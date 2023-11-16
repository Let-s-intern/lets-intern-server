package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramHelper {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    public Long createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        Program newProgram = programMapper.toEntity(programCreateRequestDTO);
        return programRepository.save(newProgram).getId();
    }

    public Long updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

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
            program.setDueDate(programUpdateRequestDTO.getDueDate());
        }
        if(programUpdateRequestDTO.getAnnouncementDate() != null) {
            SimpleDateFormat simpleDateFormatWithTIme = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
            program.setAnnouncementDate(simpleDateFormatWithTIme.format(programUpdateRequestDTO.getAnnouncementDate()));
        }
        if(programUpdateRequestDTO.getStartDate() != null) {
            program.setStartDate(simpleDateFormat.format(programUpdateRequestDTO.getStartDate()));
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
        if(programUpdateRequestDTO.getQuestions() != null) {
            program.setQuestions(programUpdateRequestDTO.getQuestions());
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

    public ProgramDetailVo getProgramDetailVo(Long programId) {
        ProgramDetailVo programDetailVo = programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        return programDetailVo;
    }
}
