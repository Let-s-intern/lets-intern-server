package com.letsintern.letsintern.domain.mission.mapper;

import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

    public Mission toEntity(Program program, MissionCreateDTO missionCreateDTO) {
        return Mission.of(program, missionCreateDTO.getTitle(), missionCreateDTO.getContents(), missionCreateDTO.getTh(), missionCreateDTO.getStartDate(), missionCreateDTO.getEndDate());
    }

    public MissionIdResponse toMissionIdResponse(Long missionId) {
        return MissionIdResponse.from(missionId);
    }

}
