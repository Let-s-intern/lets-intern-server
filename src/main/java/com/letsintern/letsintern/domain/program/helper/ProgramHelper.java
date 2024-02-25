package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.faq.repository.FaqRepository;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramFeeType;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.exception.ChallengeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.exception.RefundProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProgramHelper {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    private final FaqRepository faqRepository;
    private final ReviewRepository reviewRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationHelper applicationHelper;

    private final ZoomMeetingApiHelper zoomMeetingApiHelper;

    public Long createProgram(ProgramCreateRequestDTO programCreateRequestDTO) throws Exception {
        ZoomMeetingCreateResponse zoomMeetingCreateResponse = null;

        // 이용료 or 보증금 프로그램 정보 입력 확인
        if(programCreateRequestDTO.getFeeType().equals(ProgramFeeType.CHARGE) || programCreateRequestDTO.getFeeType().equals(ProgramFeeType.REFUND)) {
            if(programCreateRequestDTO.getFeeTotal() == null || programCreateRequestDTO.getAccountType() == null || programCreateRequestDTO.getAccountNumber() == null || programCreateRequestDTO.getFeeDueDate() == null) {
                throw RefundProgramCreateBadRequest.EXCEPTION;
            }
        }

        // 챌린지 프로그램 정보 입력 확인
        if(programCreateRequestDTO.getType().equals(ProgramType.CHALLENGE_HALF) || programCreateRequestDTO.getType().equals(ProgramType.CHALLENGE_FULL)) {
            if(programCreateRequestDTO.getTopic() == null || programCreateRequestDTO.getOpenKakaoLink() == null) {
                throw ChallengeProgramCreateBadRequest.EXCEPTION;
            }
        }

        // [렛츠챗/챌린지] Zoom Meeting 생성
        if(programCreateRequestDTO.getType().equals(ProgramType.LETS_CHAT) || programCreateRequestDTO.getType().equals(ProgramType.CHALLENGE_HALF) || programCreateRequestDTO.getType().equals(ProgramType.CHALLENGE_FULL)) {
            zoomMeetingCreateResponse = zoomMeetingApiHelper.createMeeting(
                    programCreateRequestDTO.getType(),
                    programCreateRequestDTO.getTitle(),
                    programCreateRequestDTO.getTh(),
                    programCreateRequestDTO.getStartDate());
        }

        Program savedProgram = programRepository.save(programMapper.toEntity(programCreateRequestDTO, zoomMeetingCreateResponse));
        return savedProgram.getId();
    }

    public Long updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        if(programUpdateRequestDTO.getType() != null) {
            program.setType(programUpdateRequestDTO.getType());
        }
        if(programUpdateRequestDTO.getTh() != null) {
            program.setTh(programUpdateRequestDTO.getTh());
        }
        if(programUpdateRequestDTO.getTitle() != null) {
            program.setTitle(programUpdateRequestDTO.getTitle());
        }
        if(programUpdateRequestDTO.getHeadcount() != null) {
            program.setHeadcount(programUpdateRequestDTO.getHeadcount());
        }
        if(programUpdateRequestDTO.getDueDate() != null) {
            program.setDueDate(programUpdateRequestDTO.getDueDate());
            if(program.getDueDate().isAfter(LocalDateTime.now()))
                program.setStatus(ProgramStatus.OPEN);
            else
                program.setStatus(ProgramStatus.CLOSED);
        }
        if(programUpdateRequestDTO.getAnnouncementDate() != null) {
            program.setAnnouncementDate(programUpdateRequestDTO.getAnnouncementDate());
        }
        if(programUpdateRequestDTO.getStartDate() != null) {
            program.setStartDate(programUpdateRequestDTO.getStartDate());
        }
        if(programUpdateRequestDTO.getEndDate() != null) {
            program.setEndDate(programUpdateRequestDTO.getEndDate());
        }
        if(programUpdateRequestDTO.getContents() != null) {
            program.setContents(programUpdateRequestDTO.getContents());
        }
        if(programUpdateRequestDTO.getWay() != null) {
            program.setWay(programUpdateRequestDTO.getWay());
        }
        if(programUpdateRequestDTO.getLocation() != null) {
            program.setLocation(programUpdateRequestDTO.getLocation());
        }
        if(programUpdateRequestDTO.getNotice() != null) {
            program.setNotice(programUpdateRequestDTO.getNotice());
        }
        if(programUpdateRequestDTO.getStatus() != null) {
            program.setStatus(programUpdateRequestDTO.getStatus());
        }
        if(programUpdateRequestDTO.getIsVisible() != null) {
            program.setIsVisible(programUpdateRequestDTO.getIsVisible());
        }
        if(programUpdateRequestDTO.getFaqIdList() != null) {
            program.setFaqListStr(StringUtils.listToString(programUpdateRequestDTO.getFaqIdList()));
        }

        if(programUpdateRequestDTO.getLink() != null) {
            program.setLink(programUpdateRequestDTO.getLink());
        }
        if(programUpdateRequestDTO.getLinkPassword() != null) {
            program.setLinkPassword(programUpdateRequestDTO.getLinkPassword());
        }
        if(programUpdateRequestDTO.getFeeType() != null) {
            program.setFeeType(programUpdateRequestDTO.getFeeType());
        }
        if(programUpdateRequestDTO.getFeeTotal() != null) {
            program.setFeeTotal(programUpdateRequestDTO.getFeeTotal());
        }
        if(programUpdateRequestDTO.getFeeDueDate() != null) {
            program.setFeeDueDate(programUpdateRequestDTO.getFeeDueDate());
        }
        if(programUpdateRequestDTO.getAccountType() != null) {
            program.setAccountType(programUpdateRequestDTO.getAccountType());
        }
        if(programUpdateRequestDTO.getAccountNumber() != null) {
            program.setAccountNumber(programUpdateRequestDTO.getAccountNumber());
        }

        if(programUpdateRequestDTO.getTopic() != null) {
            program.setTopic(programUpdateRequestDTO.getTopic());
        }
        if(programUpdateRequestDTO.getOpenKakaoLink() != null) {
            program.setOpenKakaoLink(programUpdateRequestDTO.getOpenKakaoLink());
        }

        return program.getId();
    }

    public ProgramListDTO getProgramThumbnailList(String type, Pageable pageable) {
        Page<ProgramThumbnailVo> programList;
        if(type != null) programList = programRepository.findProgramThumbnailsByType(type, pageable);
        else programList = programRepository.findProgramThumbnails(pageable);

        return programMapper.toProgramListDTO(programList);
    }

    public ProgramDetailDTO getProgramDetailVo(Long programId, Long userId) {
        ProgramDetailVo programDetailVo = programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        List<Long> faqIdList = StringUtils.stringToList(programDetailVo.getFaqListStr());
        List<FaqVo> faqList = new ArrayList<>();
        for(Long id : faqIdList) {
            faqList.add(faqRepository.findVoById(Long.valueOf(id)));
        }
      
        List<ReviewVo> reviewList = reviewRepository.findAllVosByProgramType(programDetailVo.getType());

        List<ApplicationWishJob> wishJobList = ApplicationWishJob.getApplicationWishJobListByProgramTopic(programDetailVo.getTopic());

        /* 회원 - 기존 신청 내역 존재 확인 */
        if(userId != null && applicationHelper.checkUserApplicationExist(programId, userId)) {
            return ProgramDetailDTO.of(programDetailVo, true, faqList, reviewList, wishJobList);
        }

        return ProgramDetailDTO.of(programDetailVo, false, faqList, reviewList, wishJobList);
    }

    public AdminProgramListDTO getAdminProgramList(String type, Integer th, Pageable pageable) {
        if(type != null && th != null) {
            return AdminProgramListDTO.from(programRepository.findAllAdminByTypeAndTh(type, th, pageable));
        }

        if(type != null) {
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
}
