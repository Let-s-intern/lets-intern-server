package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.faq.repository.FaqRepository;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.MailType;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.exception.ChallengeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.exception.ChargeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.exception.RefundProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.common.util.EmailUtils;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProgramHelper {
    private final static int RANDOM_NUMBER_LENGTH = 4;
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    private final FaqRepository faqRepository;
    private final ReviewRepository reviewRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationHelper applicationHelper;
    private final EmailUtils emailUtils;

    public int generateRandomNumber() {
        SecureRandom secureRandom = new SecureRandom();
        int upperLimit = (int) Math.pow(10, RANDOM_NUMBER_LENGTH);
        return secureRandom.nextInt(upperLimit);
    }

    public String getProgramMentorPassword(Long programId) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        return program.getMentorPassword();
    }

    public LetsChatPriorSessionNoticeResponse getLetsChatPriorSessionNotice(Program program) {
        List<String> applyMotiveList = applicationRepository.findAllApplyMotiveByProgramId(program.getId());
        List<String> preQuestionsList = applicationRepository.findAllPreQuestionsByProgramId(program.getId());
        return programMapper.toLetsChatPriorSessionNoticeResponse(program, applyMotiveList, preQuestionsList);
    }

    public LetsChatAfterSessionNoticeResponse getLetsChatAfterSessionNotice(String title, Long programId) {
        List<String> reviewList = reviewRepository.findAllReviewContentsByProgramId(programId);
        return programMapper.toLetsChatAfterSessionNoticeResponse(title, reviewList);
    }

    public ProgramListDTO getProgramThumbnailList(String type, Pageable pageable) {
        Page<ProgramThumbnailVo> programList;
        if (type != null) programList = programRepository.findProgramThumbnailsByType(type, pageable);
        else programList = programRepository.findProgramThumbnails(pageable);

        return programMapper.toProgramListDTO(programList);
    }

    public ProgramDetailDTO getProgramDetailVo(Long programId, Long userId) {
        ProgramDetailVo programDetailVo = findProgramDetailVoOrThrow(programId);

        List<Long> faqIdList = StringUtils.stringToList(programDetailVo.getFaqListStr());
        List<FaqVo> faqList = new ArrayList<>();
        for (Long id : faqIdList) {
            faqList.add(faqRepository.findVoById(Long.valueOf(id)));
        }

        List<ReviewVo> reviewList = reviewRepository.findAllVosByProgramType(programDetailVo.getType());
        List<ApplicationWishJob> wishJobList = ApplicationWishJob.getApplicationWishJobListByProgramTopic(programDetailVo.getTopic());

        /* 회원 - 기존 신청 내역 존재 확인 */
        if (userId != null && applicationHelper.checkUserApplicationExist(programId, userId)) {
            return ProgramDetailDTO.of(programDetailVo, true, faqList, reviewList, wishJobList);
        } else {
            return ProgramDetailDTO.of(programDetailVo, false, faqList, reviewList, wishJobList);
        }
    }

    public AdminProgramListDTO getAdminProgramList(String type, Integer th, Pageable pageable) {
        if (type != null && th != null) {
            return AdminProgramListDTO.from(programRepository.findAllAdminByTypeAndTh(type, th, pageable));
        }

        if (type != null) {
            return AdminProgramListDTO.from(programRepository.findAllAdminByType(type, pageable));
        }

        return AdminProgramListDTO.from(programRepository.findAllAdmin(pageable));
    }

    public Page<UserProgramVo> getAdminUserProgramList(Long userId, Pageable pageable) {
        return applicationRepository.findAllProgramByUserId(userId, pageable);
    }

    public Program getExistingProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        return program;
    }

    public void saveFinalHeadCount(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        program.setFinalHeadCount(applicationRepository.countAllByProgramIdAndStatus(programId, ApplicationStatus.IN_PROGRESS));
    }

    public String createChallengeProgramEmailByMailType(Program program, MailType mailType) {
        switch (mailType) {
            case APPROVED:
                return emailUtils.getChallengeApprovedEmailText(program);
            case FEE_CONFIRMED:
                return emailUtils.getChallengeFeeConfirmedEmailText(program);
        }
        return null;
    }

    public String parseToFaqIdList(ProgramUpdateRequestDTO programUpdateRequestDTO) {
        return StringUtils.listToString(programUpdateRequestDTO.getFaqIdList());
    }

    public ProgramStatus getProgramStatusForDueDate(ProgramUpdateRequestDTO programUpdateRequestDTO) {
        if (programUpdateRequestDTO.getDueDate().isAfter(LocalDateTime.now())) {
            return ProgramStatus.OPEN;
        } else {
            return ProgramStatus.CLOSED;
        }
    }

    public void validateChargeTypeProgramInput(ProgramCreateRequestDTO programCreateRequestDTO) {
        if (Objects.isNull(programCreateRequestDTO.getFeeCharge())
                || Objects.isNull(programCreateRequestDTO.getAccountType())
                || Objects.isNull(programCreateRequestDTO.getAccountNumber())
                || Objects.isNull(programCreateRequestDTO.getFeeDueDate())) {
            throw ChargeProgramCreateBadRequest.EXCEPTION;
        }
    }

    public void validateRefundTypeProgramInput(ProgramCreateRequestDTO programCreateRequestDTO) {
        if (Objects.isNull(programCreateRequestDTO.getFeeRefund())
                || Objects.isNull(programCreateRequestDTO.getFeeCharge())
                || Objects.isNull(programCreateRequestDTO.getAccountType())
                || Objects.isNull(programCreateRequestDTO.getAccountNumber())
                || Objects.isNull(programCreateRequestDTO.getFeeDueDate())) {
            throw RefundProgramCreateBadRequest.EXCEPTION;
        }
    }

    public void validateChallengeTypeProgramInput(ProgramCreateRequestDTO programCreateRequestDTO) {
        if (Objects.isNull(programCreateRequestDTO.getTopic())
                || Objects.isNull(programCreateRequestDTO.getOpenKakaoLink())
                || Objects.isNull(programCreateRequestDTO.getOpenKakaoPassword())) {
            throw ChallengeProgramCreateBadRequest.EXCEPTION;
        }
    }

    public Program findProgramOrThrow(Long programId) {
        return programRepository.findById(programId)
                .orElseThrow(() -> ProgramNotFound.EXCEPTION);
    }

    public ProgramDetailVo findProgramDetailVoOrThrow(Long programId) {
        return programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> ProgramNotFound.EXCEPTION);
    }

    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }
}
