package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.faq.helper.FaqHelper;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.ChallengeTopic;
import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.LetsChatMentorPasswordDto;
import com.letsintern.letsintern.domain.notice.program.dto.response.*;
import com.letsintern.letsintern.domain.program.helper.LetsChatHelper;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.helper.ZoomMeetingApiHelper;
import com.letsintern.letsintern.domain.program.mapper.LetsChatMapper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.helper.PaymentHelper;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatMentorInfoVo;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatDetailVo;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service("LETS_CHAT")
public class LetsChatServiceImpl implements ProgramService {

    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;
    private final LetsChatHelper letsChatHelper;
    private final LetsChatMapper letsChatMapper;
    private final PaymentHelper paymentHelper;
    private final ZoomMeetingApiHelper zoomMeetingApiHelper;
    private final FaqHelper faqHelper;
    private final ReviewHelper reviewHelper;
    private final ApplicationHelper applicationHelper;

    @Override
    public BaseProgramResponseDto<?> getProgramForAdmin(Long programId) {
        final LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
        return letsChatMapper.toBaseProgramResponseDto(letsChat);
    }

    @Override
    public ProgramListResponseDto getProgramList() {
        return null;
    }

    @Override
    public ProgramDetailResponseDto<?> getProgramDetail(Long programId, PrincipalDetails principalDetails) {
        LetsChatDetailVo letsChatDetailVo = letsChatHelper.findLetsChatDetailVoOrThrow(programId);
        List<FaqVo> faqList = faqHelper.createFaqVoList(letsChatDetailVo.faqListStr());
        List<ReviewVo> reviewList = reviewHelper.findAllVosByProgramType(letsChatDetailVo.programType());
        List<ApplicationWishJob> wishJobList = ApplicationWishJob.getApplicationWishJobListByProgramTopic(ChallengeTopic.ALL);
        boolean existApplication = checkExistingApplication(principalDetails, programId);
        return programMapper.toProgramDetailResponseDto(letsChatDetailVo, existApplication, faqList, reviewList, wishJobList);
    }

    @Override
    public void createProgram(BaseProgramRequestDto requestDto) {
        paymentHelper.validatePaymentProgramInput(requestDto.paymentInfo());
        ZoomMeetingCreateResponse zoomMeetingCreateResponse = zoomMeetingApiHelper.createMeeting(requestDto.programInfo());
        String mentorPassword = letsChatHelper.generateMentorPassword();
        LetsChat newLetsChat = createLetsChatAndSave(requestDto, zoomMeetingCreateResponse, mentorPassword);
        checkPaymentInputAndCreateAndSave(requestDto.paymentInfo(), newLetsChat);
    }

    @Override
    public void updateProgram(Long programId, BaseProgramRequestDto requestDto) {
        LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
        ProgramStatus programStatus = programHelper.getProgramStatusForDueDate(requestDto.programInfo());
        String stringFaqList = programHelper.parseToFaqIdList(requestDto.programInfo().faqIdList());
        letsChat.updateLetsChat(requestDto, programStatus, stringFaqList);
        checkPaymentInputAndUpdate(requestDto.paymentInfo(), letsChat);
    }

    @Override
    public void deleteProgram(Long programId) {
        final LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
        letsChatHelper.deleteLetsChat(letsChat);
    }

    public LetsChatMentorPasswordDto getMentorPassword(Long programId) {
        final LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
        return letsChatMapper.toLetsChatMentorPasswordDto(letsChat.getMentorPassword());
    }

    public LetsChatMentorPriorSessionResponseDto getMentorPriorSessionInfo(Long programId, LetsChatMentorPasswordDto letsChatMentorPasswordDto) {
        LetsChatMentorInfoVo letsChatMentorInfoVo = letsChatHelper.findLetsChatMentorInfoVo(programId);
        List<String> applyMotiveList = applicationHelper.getApplicationApplyMotiveList(programId);
        List<String> preQuestionList = applicationHelper.getApplicationPreQuestionList(programId);
        return letsChatMapper.toLetsChatMentorPriorSessionResponseDto(letsChatMentorInfoVo, applyMotiveList, preQuestionList);
    }

    public LetsChatMentorAfterSessionResponseDto getMentorAfterSessionInfo(Long programId, LetsChatMentorPasswordDto letsChatMentorPasswordDto) {
        final LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
        List<String> reviewList = reviewHelper.findAllReviewContentsByProgramId(programId);
        return letsChatMapper.toLetsChatMentorAfterSessionResponseDto(letsChat.getTitle(), reviewList);
    }

    private boolean checkExistingApplication(PrincipalDetails principalDetails, Long programId) {
        return !Objects.isNull(principalDetails)
                && applicationHelper.checkUserApplicationExist(programId, principalDetails.getUser().getId());
    }

    private LetsChat createLetsChatAndSave(BaseProgramRequestDto baseProgramRequestDto,
                                           ZoomMeetingCreateResponse zoomMeetingCreateResponse,
                                           String mentorPassword) {
        LetsChat newLetsChat = letsChatMapper.toEntityLetsChat(baseProgramRequestDto, zoomMeetingCreateResponse, mentorPassword);
        return letsChatHelper.saveLetsChat(newLetsChat);
    }

    private void checkPaymentInputAndCreateAndSave(PaymentRequestDto paymentRequestDto, LetsChat letsChat) {
        if(Objects.isNull(paymentRequestDto)) return;
        Payment payment = Payment.createPayment(paymentRequestDto);
        letsChat.addPayment(payment);
    }

    private void checkPaymentInputAndUpdate(PaymentRequestDto paymentRequestDto, LetsChat letsChat) {
        if(Objects.isNull(paymentRequestDto)) return;
        Payment payment = letsChat.getPayment();
        payment.updatePayment(paymentRequestDto);
    }
}
