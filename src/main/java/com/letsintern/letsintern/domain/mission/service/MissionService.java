package com.letsintern.letsintern.domain.mission.service;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import com.letsintern.letsintern.domain.contents.helper.ContentsHelper;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.request.MissionUpdateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminSimpleListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionMyDashboardListResponse;
import com.letsintern.letsintern.domain.mission.helper.MissionHelper;
import com.letsintern.letsintern.domain.mission.mapper.MissionMapper;
import com.letsintern.letsintern.domain.mission.vo.MissionAdminDetailVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;
    private final ProgramHelper programHelper;
    private final ContentsHelper contentsHelper;

    @Transactional
    public MissionIdResponse createMission(Long programId, MissionCreateDTO missionCreateDTO) {
        Program program = programHelper.findProgramOrThrow(programId);
        Contents essentialContents = checkContentsTypeInputAndFindOrThrowNull(ContentsType.ESSENTIAL, missionCreateDTO);
        Contents additionalContents = checkContentsTypeInputAndFindOrThrowNull(ContentsType.ADDITIONAL, missionCreateDTO);
        Contents limitedContents = checkContentsTypeInputAndFindOrThrowNull(ContentsType.LIMITED, missionCreateDTO);
        Mission newMission = createMissionAndSave(program, missionCreateDTO, essentialContents, additionalContents, limitedContents);
        return missionMapper.toMissionIdResponse(newMission.getId());
    }

    @Transactional(readOnly = true)
    public MissionAdminSimpleListResponse getMissionAdminSimpleList(Long programId) {
        final Program program = programHelper.findProgramOrThrow(programId);
        return missionMapper.toMissionAdminSimpleListResponse(program.getHeadcount(), program.getStartDate(), missionHelper.getMissionAdminSimpleList(programId));
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
    public MissionMyDashboardListResponse getMissionMyDashboardList(Long programId, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return missionMapper.toMissionMyDashboardListResponse(missionHelper.getMissionMyDashboardList(programId, user.getId()));
    }

    @Transactional(readOnly = true)
    public Object getMissionMyDashboardDetail(Long missionId, MissionDashboardListStatus status, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return missionHelper.getMissionMyDashboardDetail(missionId, status, user.getId());
    }

    private Mission createMissionAndSave(Program program, MissionCreateDTO missionCreateDTO, Contents essentialContents, Contents additionalContents, Contents limitedContents) {
        Mission mission = missionMapper.toEntity(program, missionCreateDTO, essentialContents, additionalContents, limitedContents);
        return missionHelper.saveMission(mission);
    }

    private Contents checkContentsTypeInputAndFindOrThrowNull(ContentsType contentsType, MissionCreateDTO missionCreateDTO) {
        if (Objects.isNull(missionCreateDTO.getEssentialContentsTopic())) return null;
        return contentsHelper.findContentsByContentsTopicOrThrow(contentsType, missionCreateDTO.getEssentialContentsTopic());
    }
}
