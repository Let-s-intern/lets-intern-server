package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramHelper {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    private Integer findMaxTh(ProgramType type) {
        return programRepository.maxTh(type);
    }

    public Long createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        Program newProgram = programMapper.toEntity(programCreateRequestDTO, findMaxTh(programCreateRequestDTO.getType()) + 1);
        return programRepository.save(newProgram).getId();
    }

    public Long updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if(programUpdateRequestDTO.getType() != null) {
            program.setType(programUpdateRequestDTO.getType());
        }
        if(programUpdateRequestDTO.getTh() != null) {
            program.setTh(programUpdateRequestDTO.getTh());
        }
        if(programUpdateRequestDTO.getDueDate() != null) {
            program.setDueDate(programUpdateRequestDTO.getDueDate());
        }
        if(programUpdateRequestDTO.getAnnouncementDate() != null) {
            SimpleDateFormat simpleDateFormatWithTIme = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            program.setAnnouncementDate(simpleDateFormatWithTIme.format(programUpdateRequestDTO.getAnnouncementDate()));
        }
        if(programUpdateRequestDTO.getStartDate() != null) {
            program.setStartDate(simpleDateFormat.format(programUpdateRequestDTO.getStartDate()));
        }
        if(programUpdateRequestDTO.getStatus() != null) {
            program.setStatus(programUpdateRequestDTO.getStatus());
        }

        return program.getId();
    }

    public ProgramListDTO getProgramList(Pageable pageable) {
        List<ProgramThumbnailVo> openProgramList =  programRepository.findProgramThumbnailByStatus(ProgramStatus.OPEN, pageable);
        List<ProgramThumbnailVo> closedProgramList = programRepository.findProgramThumbnailByStatus(ProgramStatus.CLOSED, pageable);
        return programMapper.toProgramListDTO(openProgramList, closedProgramList);
    }

    public ProgramListDTO getProgramTypeList(String type, Pageable pageable) {
        List<ProgramThumbnailVo> openProgramList =  programRepository.findProgramThumbnailByTypeAndStatus(type, ProgramStatus.OPEN, pageable);
        List<ProgramThumbnailVo> closedProgramList = programRepository.findProgramThumbnailByTypeAndStatus(type, ProgramStatus.CLOSED, pageable);
        return programMapper.toProgramListDTO(openProgramList, closedProgramList);
    }
}
