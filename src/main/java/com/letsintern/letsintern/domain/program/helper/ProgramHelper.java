package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.faq.dto.request.FaqCreateDTO;
import com.letsintern.letsintern.domain.faq.repository.FaqRepository;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.AdminProgramListDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Long createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        Program savedProgram = programRepository.save(programMapper.toEntity(programCreateRequestDTO));
        return savedProgram.getId();
    }

    public Long updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) throws ParseException {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");

        if(programUpdateRequestDTO.getType() != null) {
            program.setType(programUpdateRequestDTO.getType());
        }
        if(programUpdateRequestDTO.getTh() != null) {
            program.setTh(programUpdateRequestDTO.getTh());
        }
        if(programUpdateRequestDTO.getTitle() != null) {
            program.setTitle(program.getTitle());
        }
        if(programUpdateRequestDTO.getHeadcount() != null) {
            program.setHeadcount(program.getHeadcount());
        }
        if(programUpdateRequestDTO.getDueDate() != null) {
            program.setDueDate(simpleDateFormat.parse(programUpdateRequestDTO.getDueDate()));
        }
        if(programUpdateRequestDTO.getAnnouncementDate() != null) {
            program.setAnnouncementDate(programUpdateRequestDTO.getAnnouncementDate());
        }
        if(programUpdateRequestDTO.getStartDate() != null) {
            program.setStartDate(programUpdateRequestDTO.getStartDate());
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
        if(programUpdateRequestDTO.getLink() != null) {
            program.setLink(programUpdateRequestDTO.getLink());
        }
        if(programUpdateRequestDTO.getNotice() != null) {
            program.setNotice(programUpdateRequestDTO.getNotice());
        }
        if(programUpdateRequestDTO.getStatus() != null) {
            program.setStatus(programUpdateRequestDTO.getStatus());
        }
        if(programUpdateRequestDTO.getIsApproved() != null) {
            program.setIsApproved(programUpdateRequestDTO.getIsApproved());
        }
        if(programUpdateRequestDTO.getIsVisible() != null) {
            program.setIsVisible(programUpdateRequestDTO.getIsVisible());
        }
        if(programUpdateRequestDTO.getFaqIdList() != null) {
            program.setFaqListStr(programUpdateRequestDTO.getFaqIdList().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        return program.getId();
    }

    @Transactional
    public void updateProgramHeadCount(Long programId) {
        Program program = getExistingProgram(programId);
        program.setHeadcount(program.getHeadcount() + 1);

        if(program.getMaxHeadcount() > 0 && program.getHeadcount() >= program.getMaxHeadcount()) {
            program.setStatus(ProgramStatus.CLOSED);
        }
    }

    public ProgramListDTO getProgramThumbnailList(String type, Pageable pageable) {
        List<ProgramThumbnailVo> programList;
        if(type != null) programList = programRepository.findProgramThumbnailsByType(type, pageable);
        else programList = programRepository.findProgramThumbnails(pageable);

        return programMapper.toProgramListDTO(programList);
    }

    public ProgramDetailDTO getProgramDetailVo(Long programId, Long userId) {
        ProgramDetailVo programDetailVo = programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        List<Integer> faqIdList = StringUtils.stringToList(programDetailVo.getFaqListStr());
        List<FaqVo> faqList = new ArrayList<>();
        for(Integer id : faqIdList) {
            faqList.add(faqRepository.findVoById(Long.valueOf(id)));
        }
        List<ReviewVo> reviewList = reviewRepository.findAllVosByProgramId(programId);

        if(userId != null) {
            Application application = applicationRepository.findByProgramIdAndUserId(programId, userId);
            if(application != null) return ProgramDetailDTO.of(programDetailVo, true, faqList, reviewList);
        }

        return ProgramDetailDTO.of(programDetailVo, false, faqList, reviewList);
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

    public Program getExistingProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        return program;
    }
}
