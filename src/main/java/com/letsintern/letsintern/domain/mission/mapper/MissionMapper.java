package com.letsintern.letsintern.domain.mission.mapper;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminSimpleListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionMyDashboardListResponse;
import com.letsintern.letsintern.domain.mission.vo.MissionAdminSimpleVo;
import com.letsintern.letsintern.domain.mission.vo.MissionAdminVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardListVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MissionMapper {

    public Mission toEntity(Program program, MissionCreateDTO missionCreateDTO, Contents essentialContents, Contents additionalContents, Contents limitedContents) {
        return Mission.of(program, missionCreateDTO, essentialContents, additionalContents, limitedContents);
    }

    public MissionIdResponse toMissionIdResponse(Long missionId) {
        return MissionIdResponse.from(missionId);
    }

    public MissionAdminSimpleListResponse toMissionAdminSimpleListResponse(List<MissionAdminSimpleVo> missionAdminSimpleList) {
        return MissionAdminSimpleListResponse.from(missionAdminSimpleList);
    }

    public MissionAdminListResponse toMissionAdminListResponse(Page<MissionAdminVo> missionAdminList) {
        return MissionAdminListResponse.from(missionAdminList);
    }

    public MissionMyDashboardListResponse toMissionMyDashboardListResponse(List<MissionMyDashboardListVo> missionMyDashboardList) {
        return MissionMyDashboardListResponse.from(missionMyDashboardList);
    }

}
