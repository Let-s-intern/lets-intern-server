package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.helper.PaymentHelper;
import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.helper.ChallengeHelper;
import com.letsintern.letsintern.domain.program.mapper.ChallengeMapper;
import com.letsintern.letsintern.global.utils.EnumValueUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.letsintern.letsintern.global.utils.EnumValueUtils.toEntityCode;

@RequiredArgsConstructor
@Service("CHALLENGE")
public class ChallengeServiceImpl implements ProgramService {
    private final ChallengeHelper challengeHelper;
    private final ChallengeMapper challengeMapper;
    private final PaymentHelper paymentHelper;

    @Override
    public void createProgram(BaseProgramRequestDto baseProgramRequestDto) {
        validatePaymentProgramInput(baseProgramRequestDto.paymentInfo());
        challengeHelper.validateChallengeTypeProgramInput(baseProgramRequestDto.challengeInfo());
        Challenge newChallenge = createChallengeAndSave(baseProgramRequestDto);
        checkPaymentInputAndCreateAndSave(baseProgramRequestDto.paymentInfo(), newChallenge);
    }

    @Override
    public void updateProgram(BaseProgramRequestDto baseProgramRequestDto) {
        baseProgramRequestDto
    }

    private void checkPaymentInputAndCreateAndSave(PaymentRequestDto paymentRequestDto, Challenge challenge) {
        if (Objects.isNull(paymentRequestDto)) return;
        Payment payment = Payment.createPayment(paymentRequestDto);
        challenge.addPayment(payment);
    }

    private void checkPaymentInputAndUpdate() {

    }

    private void validatePaymentProgramInput(PaymentRequestDto requestDto) {
        if (Objects.isNull(requestDto)) return;
        if (FeeType.REFUND.equals(toEntityCode(FeeType.class, requestDto.feeType())))
            paymentHelper.validateRefundTypeProgramInput(requestDto);
        if (FeeType.CHARGE.equals(toEntityCode(FeeType.class, requestDto.feeType())))
            paymentHelper.validateChargeTypeProgramInput(requestDto);
    }

    private Challenge createChallengeAndSave(BaseProgramRequestDto baseProgramRequestDto) {
        Challenge challenge = challengeMapper.toEntityChallenge(baseProgramRequestDto);
        return challengeHelper.saveChallenge(challenge);
    }
}
