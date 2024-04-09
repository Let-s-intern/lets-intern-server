package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.payment.helper.PaymentHelper;
import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.helper.ChallengeHelper;
import com.letsintern.letsintern.domain.program.mapper.ChallengeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("CHALLENGE")
public class ChallengeServiceImpl implements ProgramService {
    private final ChallengeHelper challengeHelper;
    private final ChallengeMapper challengeMapper;
    private final PaymentHelper paymentHelper;

    @Override
    public void createProgram(BaseProgramRequestDto baseProgramRequestDto) {
        paymentHelper.validatePaymentProgramInput(baseProgramRequestDto.paymentInfo());
        challengeHelper.validateChallengeTypeProgramInput(baseProgramRequestDto.challengeInfo());
        createChallengeAndSave(baseProgramRequestDto);
    }

    @Override
    public void updateProgram(BaseProgramRequestDto baseProgramRequestDto) {


    }

    private void createChallengeAndSave(BaseProgramRequestDto baseProgramRequestDto) {
        Challenge challenge = challengeMapper.toEntityChallenge(baseProgramRequestDto);
        challengeHelper.saveChallenge(challenge);
    }
}
