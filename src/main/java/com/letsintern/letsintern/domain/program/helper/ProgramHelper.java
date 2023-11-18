package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.faq.dto.FaqDTO;
import com.letsintern.letsintern.domain.faq.repository.FaqRepository;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramAdminListDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramHelper {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    private final FaqRepository faqRepository;
    private final ReviewRepository reviewRepository;

    public Long createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        Program savedProgram = programRepository.save(programMapper.toEntity(programCreateRequestDTO));

        if(programCreateRequestDTO.getFaqDTOList() != null) {
            for(FaqDTO faqDTO : programCreateRequestDTO.getFaqDTOList()) {
                faqRepository.save(Faq.of(savedProgram, faqDTO.getQuestion(), faqDTO.getAnswer()));
            }
        }

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
        if(programUpdateRequestDTO.getStatus() != null) {
            program.setStatus(programUpdateRequestDTO.getStatus());
        }
        if(programUpdateRequestDTO.getIsApproved() != null) {
            program.setIsApproved(programUpdateRequestDTO.getIsApproved());
        }
        if(programUpdateRequestDTO.getIsVisible() != null) {
            program.setIsVisible(programUpdateRequestDTO.getIsVisible());
        }

        return program.getId();
    }

    public ProgramListDTO getProgramList(Pageable pageable) {
        List<ProgramThumbnailVo> programList =  programRepository.findProgramThumbnails(pageable);
        return programMapper.toProgramListDTO(programList);
    }

    public ProgramListDTO getProgramTypeList(String type, Pageable pageable) {
        List<ProgramThumbnailVo> programList =  programRepository.findProgramThumbnailsByType(type, pageable);
        return programMapper.toProgramListDTO(programList);
    }

    public ProgramDetailDTO getProgramDetailVo(Long programId) {
        ProgramDetailVo programDetailVo = programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        List<Faq> faqList = faqRepository.findAllByProgramId(programId);
        List<ReviewVo> reviewList = reviewRepository.findAllVosByProgramId(programId);

        return ProgramDetailDTO.of(programDetailVo, faqList, reviewList);
    }

    public ProgramAdminListDTO getAdminProgramList(Pageable pageable) {
        return ProgramAdminListDTO.from(programRepository.findAllAdmin(pageable));
    }
}
