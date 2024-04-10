package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.helper.PaymentHelper;
import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.program.helper.LetsChatHelper;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.helper.ZoomMeetingApiHelper;
import com.letsintern.letsintern.domain.program.mapper.LetsChatMapper;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service("LETS_CHAT")
public class LetsChatServiceImpl implements ProgramService {

    private final ProgramHelper programHelper;
    private final LetsChatHelper letsChatHelper;
    private final LetsChatMapper letsChatMapper;

    private final PaymentHelper paymentHelper;
    private final ZoomMeetingApiHelper zoomMeetingApiHelper;

    @Override
    public BaseProgramResponseDto<?> getProgramForAdmin(Long programId) {
        return null;
    }

    @Override
    public ProgramListResponseDto getProgramList() {
        return null;
    }

    @Override
    public ProgramDetailResponseDto<?> getProgramDetail(Long programId, PrincipalDetails principalDetails) {
        return null;
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
