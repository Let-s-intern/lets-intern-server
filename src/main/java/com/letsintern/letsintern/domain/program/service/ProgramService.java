package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.AdminProgramListDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;

    private final ProgramRepository programRepository;


    @Transactional
    public ProgramIdResponseDTO createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        return programMapper.toProgramIdResponseDTO(programHelper.createProgram(programCreateRequestDTO));
    }

    @Transactional
    public ProgramIdResponseDTO updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) throws ParseException {
        return programMapper.toProgramIdResponseDTO(programHelper.updateProgram(programId, programUpdateRequestDTO));
    }

    @Transactional
    public ProgramListDTO getProgramThumbnailList(String type, Pageable pageable) {
        return programHelper.getProgramThumbnailList(type, pageable);
    }

    @Transactional
    public AdminProgramListDTO getProgramAdminList(String type, Integer th, Pageable pageable) {
        return programHelper.getAdminProgramList(type, th, pageable);
    }

    public ProgramDetailDTO getProgramDetailDTO(Long programId, PrincipalDetails principalDetails) {
        if(principalDetails != null) {
            final Long userId = principalDetails.getUser().getId();
            return programHelper.getProgramDetailVo(programId, userId);
        }
        else return programHelper.getProgramDetailVo(programId, null);
    }

    public Program getProgram(Long programId) {
        return programHelper.getExistingProgram(programId);
    }

    public void deleteProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        programRepository.delete(program);
    }
}
