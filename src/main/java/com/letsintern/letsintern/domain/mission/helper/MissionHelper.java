package com.letsintern.letsintern.domain.mission.helper;

import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.exception.MissionNotFound;
import com.letsintern.letsintern.domain.mission.mapper.MissionMapper;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.mission.vo.*;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

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


    public List<MissionAdminSimpleVo> getMissionAdminSimpleList(Long programId) {
        return missionRepository.getMissionAdminSimpleList(programId);
    }

    public Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable) {
        return missionRepository.getMissionAdminList(programId, pageable);
    }

    public MissionDashboardVo getDailyMission(Long programId, LocalDateTime startDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(LocalDate.from(startDate), today);
        return missionRepository.getMissionDashboardVo(programId, period.getDays() + 1).orElse(null);
    }

    public MissionMyDashboardVo getDailyMissionDetail(Long programId, LocalDateTime startDate, Long userId) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(LocalDate.from(startDate), today);
        return missionRepository.getMissionMyDashboardVo(programId, period.getDays() + 1, userId).orElse(null);
    }

    public List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId) {
        return missionRepository.getMissionDashboardList(programId, userId);
    }

    public List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, MissionDashboardListStatus status, Long userId) {
        return missionRepository.getMissionMyDashboardList(programId, status, userId);
    }

    public Object getMissionMyDashboardDetail(Long missionId, MissionDashboardListStatus status, Long userId) {
        switch (status) {
            case DONE -> {
                return missionRepository.getMissionMyDashboardCompleted(missionId, userId).orElseThrow(() -> MissionNotFound.EXCEPTION);
            }
            case YET, ABSENT -> {
                return missionRepository.getMissionMyDashboardUncompleted(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
            }
        }
        return missionRepository.getMissionMyDashboardUncompleted(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
    }
}
