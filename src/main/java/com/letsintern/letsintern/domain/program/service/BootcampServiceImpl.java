package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.faq.helper.FaqHelper;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.Bootcamp;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.helper.ZoomMeetingApiHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.helper.PaymentHelper;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListResponseDto;
import com.letsintern.letsintern.domain.program.helper.BootcampHelper;
import com.letsintern.letsintern.domain.program.mapper.BootcampMapper;
import com.letsintern.letsintern.domain.program.vo.bootcamp.BootcampDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.common.util.StringUtils;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("BOOTCAMP")
@Transactional
public class BootcampServiceImpl implements ProgramService {
    private final BootcampHelper bootcampHelper;
    private final BootcampMapper bootcampMapper;
    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;
    private final PaymentHelper paymentHelper;
    private final ReviewHelper reviewHelper;
    private final FaqHelper faqHelper;
    private final ApplicationHelper applicationHelper;
    private final ZoomMeetingApiHelper zoomMeetingApiHelper;

    @Override
    public BaseProgramResponseDto<?> getProgramForAdmin(Long programId) {
        Bootcamp bootcamp = bootcampHelper.findBootcampOrThrow(programId);
        return programMapper.toBaseProgramResponseDto(bootcamp);
    }

    @Override
    public ProgramListResponseDto<?> getProgramList(Pageable pageable) {
        Page<ProgramThumbnailVo> programThumbnailVoPage = bootcampHelper.findProgramList(pageable);
        return programMapper.toProgramListResponseDto(programThumbnailVoPage);
    }

    @Override
    public ProgramDetailResponseDto<?> getProgramDetail(Long programId, PrincipalDetails principalDetails) {
        BootcampDetailVo bootcampDetailVo = bootcampHelper.findBootcampDetailVoOrThrow(programId);
        List<FaqVo> faqList = createFaqVoList(bootcampDetailVo);
        List<ReviewVo> reviewList = reviewHelper.findAllVosByProgramType(bootcampDetailVo.programType());
        boolean existApplication = checkExistingApplication(principalDetails, bootcampDetailVo);
        return programMapper.toProgramDetailResponseDto(bootcampDetailVo, existApplication, faqList, reviewList, null);
    }

    @Override
    public void createProgram(BaseProgramRequestDto baseProgramRequestDto) {
        paymentHelper.validatePaymentProgramInput(baseProgramRequestDto.paymentInfo());
        bootcampHelper.validateBootcampTypeProgramInput(baseProgramRequestDto.bootcampRequestDto());
        ZoomMeetingCreateResponse zoomMeetingCreateResponse
                = zoomMeetingApiHelper.createMeeting(baseProgramRequestDto.programInfo());
        Bootcamp newBootcamp = createBootcampAndSave(baseProgramRequestDto, zoomMeetingCreateResponse);
        checkPaymentInputAndCreateAndSave(baseProgramRequestDto.paymentInfo(), newBootcamp);
    }

    @Override
    public void updateProgram(Long programId, BaseProgramRequestDto baseProgramRequestDto) {
        Bootcamp bootcamp = bootcampHelper.findBootcampOrThrow(programId);
        ProgramStatus programStatus = programHelper.getProgramStatusForDueDate(baseProgramRequestDto.programInfo());
        String stringFaqList = programHelper.parseToFaqIdList(baseProgramRequestDto.programInfo().faqIdList());
        bootcamp.updateBootcamp(baseProgramRequestDto, programStatus, stringFaqList);
        checkPaymentInputAndUpdate(baseProgramRequestDto.paymentInfo(), bootcamp);
    }

    @Override
    public void deleteProgram(Long programId) {
        bootcampHelper.deleteBootcamp(programId);
    }

    private boolean checkExistingApplication(PrincipalDetails principalDetails, BootcampDetailVo bootcampDetailVo) {
        return !Objects.isNull(principalDetails)
                && applicationHelper.checkUserApplicationExist(bootcampDetailVo.bootcampId(), principalDetails.getUser().getId());
    }

    private void checkPaymentInputAndCreateAndSave(PaymentRequestDto paymentRequestDto, Bootcamp bootcamp) {
        if (Objects.isNull(paymentRequestDto)) return;
        Payment payment = Payment.createPayment(paymentRequestDto);
        bootcamp.addPayment(payment);
    }

    private void checkPaymentInputAndUpdate(PaymentRequestDto paymentRequestDto, Bootcamp bootcamp) {
        if (Objects.isNull(paymentRequestDto)) return;
        Payment payment = bootcamp.getPayment();
        payment.updatePayment(paymentRequestDto);
    }

    private Bootcamp createBootcampAndSave(BaseProgramRequestDto requestDto, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        Bootcamp bootcamp = bootcampMapper.toEntityBootcamp(requestDto, zoomMeetingCreateResponse);
        return bootcampHelper.saveBootcamp(bootcamp);
    }

    private List<FaqVo> createFaqVoList(BootcampDetailVo bootcampDetailVo) {
        List<Long> faqIdList = StringUtils.stringToList(bootcampDetailVo.faqListStr());
        return faqIdList.stream()
                .map(faqHelper::findFaqVoOrThrow)
                .collect(Collectors.toList());
    }
}
