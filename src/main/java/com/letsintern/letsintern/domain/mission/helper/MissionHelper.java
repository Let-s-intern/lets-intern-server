package com.letsintern.letsintern.domain.mission.helper;

import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.exception.MissionNotFound;
import com.letsintern.letsintern.domain.mission.mapper.MissionMapper;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
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

    public MissionAdminListResponse getMissionAdminList(Long programId, Pageable pageable) {
        return missionMapper.toMissionAdminListResponse(missionRepository.getMissionAdminList(programId, pageable));
    }

    public MissionDashboardVo getTodayMission(Long programId, LocalDateTime startDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(LocalDate.from(startDate), today);
        return missionRepository.getMissionDashboardVo(programId, period.getDays() + 1).orElse(null);
    }

    public List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId) {
        return missionRepository.getMissionDashboardList(programId, userId);
    }
}
