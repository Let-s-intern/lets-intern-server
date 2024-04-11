package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.faq.helper.FaqHelper;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.helper.PaymentHelper;
import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.program.helper.ChallengeHelper;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.helper.ZoomMeetingApiHelper;
import com.letsintern.letsintern.domain.program.mapper.ChallengeMapper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.common.util.StringUtils;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("CHALLENGE")
public class ChallengeServiceImpl implements ProgramService {
    private final ChallengeHelper challengeHelper;
    private final ChallengeMapper challengeMapper;
    private final ReviewHelper reviewHelper;
    private final PaymentHelper paymentHelper;
    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;
    private final ApplicationHelper applicationHelper;
    private final FaqHelper faqHelper;
    private final ZoomMeetingApiHelper zoomMeetingApiHelper;

    @Override
    public BaseProgramResponseDto<?> getProgramForAdmin(Long programId) {
        Challenge challenge = challengeHelper.findChallengeOrThrow(programId);
        return programMapper.toBaseProgramResponseDto(challenge);
    }

    @Override
    public ProgramListResponseDto<?> getProgramList(Pageable pageable) {
        Page<ProgramThumbnailVo> programThumbnailVos = challengeHelper.findProgramList(pageable);
        return programMapper.toProgramListResponseDto(programThumbnailVos);
    }

    @Override
    public ProgramDetailResponseDto<?> getProgramDetail(Long challengeId, PrincipalDetails principalDetails) {
        ChallengeDetailVo challengeDetailVo = challengeHelper.findChallengeDetailOrThrow(challengeId);
        List<FaqVo> faqList = createFaqVoList(challengeDetailVo);
        List<ReviewVo> reviewList = reviewHelper.findAllVosByProgramType(challengeDetailVo.programType());
        List<ApplicationWishJob> wishJobList = ApplicationWishJob.getApplicationWishJobListByProgramTopic(challengeDetailVo.topic());
        boolean existApplication = checkExistingApplication(principalDetails, challengeDetailVo);
        return programMapper.toProgramDetailResponseDto(challengeDetailVo, existApplication, faqList, reviewList, wishJobList);
    }

    @Override
    public void createProgram(BaseProgramRequestDto baseProgramRequestDto) {
        paymentHelper.validatePaymentProgramInput(baseProgramRequestDto.paymentInfo());
        challengeHelper.validateChallengeTypeProgramInput(baseProgramRequestDto.challengeInfo());
        ZoomMeetingCreateResponse zoomMeetingCreateResponse
                = zoomMeetingApiHelper.createMeeting(baseProgramRequestDto.programInfo());
        Challenge newChallenge = createChallengeAndSave(baseProgramRequestDto, zoomMeetingCreateResponse);
        checkPaymentInputAndCreateAndSave(baseProgramRequestDto.paymentInfo(), newChallenge);
    }

    @Override
    public void updateProgram(Long programId, BaseProgramRequestDto baseProgramRequestDto) {
        Challenge challenge = challengeHelper.findChallengeOrThrow(programId);
        ProgramStatus programStatus = programHelper.getProgramStatusForDueDate(baseProgramRequestDto.programInfo());
        String stringFaqList = programHelper.parseToFaqIdList(baseProgramRequestDto.programInfo().faqIdList());
        challenge.updateChallenge(baseProgramRequestDto, programStatus, stringFaqList);
        checkPaymentInputAndUpdate(baseProgramRequestDto.paymentInfo(), challenge);
    }

    @Override
    public void deleteProgram(Long challengeId) {
        challengeHelper.deleteChallenge(challengeId);
    }

    /* 회원 - 기존 신청 내역 존재 확인 */
    private boolean checkExistingApplication(PrincipalDetails principalDetails, ChallengeDetailVo challengeDetailVo) {
        return !Objects.isNull(principalDetails)
                && applicationHelper.checkUserApplicationExist(challengeDetailVo.challengeId(), principalDetails.getUser().getId());
    }

    private void checkPaymentInputAndCreateAndSave(PaymentRequestDto paymentRequestDto, Challenge challenge) {
        if (Objects.isNull(paymentRequestDto)) return;
        Payment payment = Payment.createPayment(paymentRequestDto);
        challenge.addPayment(payment);
    }

    private void checkPaymentInputAndUpdate(PaymentRequestDto paymentRequestDto, Challenge challenge) {
        if (Objects.isNull(paymentRequestDto)) return;
        Payment payment = challenge.getPayment();
        payment.updatePayment(paymentRequestDto);
    }

    private Challenge createChallengeAndSave(BaseProgramRequestDto baseProgramRequestDto,
                                             ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        Challenge challenge = challengeMapper.toEntityChallenge(baseProgramRequestDto, zoomMeetingCreateResponse);
        return challengeHelper.saveChallenge(challenge);
    }

    private List<FaqVo> createFaqVoList(ChallengeDetailVo challengeDetailVo) {
        List<Long> faqIdList = StringUtils.stringToList(challengeDetailVo.faqListStr());
        return faqIdList.stream()
                .map(faqHelper::findFaqVoOrThrow)
                .collect(Collectors.toList());
    }
}
