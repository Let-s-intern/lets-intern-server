package com.letsintern.letsintern.domain.mission.helper;

import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.mapper.MissionMapper;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionHelper {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;
    private final ProgramRepository programRepository;

    public Long createMission(Long programId, MissionCreateDTO missionCreateDTO) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        return missionRepository.save(missionMapper.toEntity(program, missionCreateDTO)).getId();
    }

}
