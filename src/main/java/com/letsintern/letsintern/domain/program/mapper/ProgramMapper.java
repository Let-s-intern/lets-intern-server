package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.dto.response.UserProgramVoResponse;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public Program toEntity(ProgramCreateRequestDTO programCreateRequestDTO, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return Program.of(programCreateRequestDTO, zoomMeetingCreateResponse);
    }

    public ProgramIdResponseDTO toProgramIdResponseDTO(Long programId) {
        return ProgramIdResponseDTO.from(programId);
    }

    public ProgramListDTO toProgramListDTO(Page<ProgramThumbnailVo> programList) {
        return ProgramListDTO.from(programList);
    }

    public UserProgramVoResponse toUserProgramVoResponse(Page<UserProgramVo> userProgramList) {
        return UserProgramVoResponse.from(userProgramList);
    }
}
