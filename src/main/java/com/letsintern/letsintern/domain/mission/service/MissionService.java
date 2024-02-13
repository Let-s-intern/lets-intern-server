package com.letsintern.letsintern.domain.mission.service;

import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.request.MissionUpdateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminSimpleListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.helper.MissionHelper;
import com.letsintern.letsintern.domain.mission.mapper.MissionMapper;
import com.letsintern.letsintern.domain.mission.dto.response.MissionMyDashboardListResponse;
import com.letsintern.letsintern.domain.mission.vo.MissionAdminDetailVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;

    @Transactional
    public MissionIdResponse createMission(Long programId, MissionCreateDTO missionCreateDTO) {
        return missionMapper.toMissionIdResponse(missionHelper.createMission(programId, missionCreateDTO));
    }

    @Transactional(readOnly = true)
    public MissionAdminSimpleListResponse getMissionAdminSimpleList(Long programId) {
        return missionMapper.toMissionAdminSimpleListResponse(missionHelper.getMissionAdminSimpleList(programId));
    }

    @Transactional(readOnly = true)
    public MissionAdminListResponse getMissionAdminList(Long programId, Pageable pageable) {
        return missionMapper.toMissionAdminListResponse(missionHelper.getMissionAdminList(programId, pageable));
    }

    @Transactional(readOnly = true)
    public MissionAdminDetailVo getMissionAdmin(Long missionId) {
        return missionHelper.getMissionAdmin(missionId);
    }

    @Transactional
    public MissionIdResponse updateMission(Long missionId, MissionUpdateDTO missionUpdateDTO) {
        return missionMapper.toMissionIdResponse(missionHelper.updateMission(missionId, missionUpdateDTO));
    }

    public void deleteMission(Long missionId) {
        missionHelper.deleteMission(missionId);
    }

    @Transactional
    public MissionMyDashboardListResponse getMissionMyDashboardList(Long programId, MissionDashboardListStatus status, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return missionMapper.toMissionMyDashboardListResponse(missionHelper.getMissionMyDashboardList(programId, status, user.getId()));
    }

    @Transactional(readOnly = true)
    public Object getMissionMyDashboardDetail(Long missionId, MissionDashboardListStatus status, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return missionHelper.getMissionMyDashboardDetail(missionId, status, user.getId());
    }

}
