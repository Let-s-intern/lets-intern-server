package com.letsintern.letsintern.domain.mission.helper;

import com.letsintern.letsintern.domain.attendance.repository.AttendanceRepository;
import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import com.letsintern.letsintern.domain.contents.exception.AdditionalContentsNotFound;
import com.letsintern.letsintern.domain.contents.exception.EssentialContentsNotFound;
import com.letsintern.letsintern.domain.contents.exception.LimitedContentsNotFound;
import com.letsintern.letsintern.domain.contents.repository.ContentsRepository;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.request.MissionUpdateDTO;
import com.letsintern.letsintern.domain.mission.exception.MissionCannotCheckDone;
import com.letsintern.letsintern.domain.mission.exception.MissionCannotRefundDone;
import com.letsintern.letsintern.domain.mission.exception.MissionCannotRefundDoneType;
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
    private final ContentsRepository contentsRepository;
    private final AttendanceRepository attendanceRepository;

    public Long createMission(Long programId, MissionCreateDTO missionCreateDTO) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        final Contents essentialContents = (missionCreateDTO.getEssentialContentsTopic() != null) ? contentsRepository.findByTypeAndTopic(ContentsType.ESSENTIAL, missionCreateDTO.getEssentialContentsTopic()).orElseThrow(() -> EssentialContentsNotFound.EXCEPTION) : null;
        final Contents additionalContents = (missionCreateDTO.getAdditionalContentsTopic() != null) ? contentsRepository.findByTypeAndTopic(ContentsType.ADDITIONAL, missionCreateDTO.getAdditionalContentsTopic()).orElseThrow(() -> AdditionalContentsNotFound.EXCEPTION) : null;
        final Contents limitedContents = (missionCreateDTO.getLimitedContentsTopic() != null) ? contentsRepository.findByTypeAndTopic(ContentsType.LIMITED, missionCreateDTO.getLimitedContentsTopic()).orElseThrow(() -> LimitedContentsNotFound.EXCEPTION) : null;

        return missionRepository.save(missionMapper.toEntity(program, missionCreateDTO, essentialContents, additionalContents, limitedContents)).getId();
    }


    public List<MissionAdminSimpleVo> getMissionAdminSimpleList(Long programId) {
        return missionRepository.getMissionAdminSimpleList(programId);
    }

    public Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable) {
        return missionRepository.getMissionAdminList(programId, pageable);
    }

    public MissionAdminDetailVo getMissionAdmin(Long missionId) {
        return missionRepository.getMissionAdminDetailVo(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
    }

    public Long updateMission(Long missionId, MissionUpdateDTO missionUpdateDTO) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);

        if(missionUpdateDTO.getType() != null)
            mission.setType(missionUpdateDTO.getType());
        if(missionUpdateDTO.getTopic() != null)
            mission.setTopic(missionUpdateDTO.getTopic());
        if(missionUpdateDTO.getStatus() != null) {
            switch (missionUpdateDTO.getStatus()) {
                case CHECK_DONE -> {
                    long notCheckedCount = attendanceRepository.countNotCheckedAttendances(missionId);
                    if(notCheckedCount > 0) throw MissionCannotCheckDone.EXCEPTION;
                }
                case REFUND_DONE -> {
                    if(!mission.getType().equals(MissionType.REFUND)) throw MissionCannotRefundDoneType.EXCEPTION;

                    long notRefundedCount = attendanceRepository.countNotRefundedAttendances(missionId);
                    if(notRefundedCount > 0) throw MissionCannotRefundDone.EXCEPTION;
                }
            }
            mission.setStatus(missionUpdateDTO.getStatus());
        }
        if(missionUpdateDTO.getRefund() != null)
            mission.setRefund(missionUpdateDTO.getRefund());
        if(missionUpdateDTO.getTitle() != null)
            mission.setTitle(missionUpdateDTO.getTitle());
        if(missionUpdateDTO.getContents() != null)
            mission.setContents(missionUpdateDTO.getContents());
        if(missionUpdateDTO.getGuide() != null)
            mission.setGuide(missionUpdateDTO.getGuide());
        if(missionUpdateDTO.getTh() != null) {
            mission.setTh(missionUpdateDTO.getTh());
            if(missionUpdateDTO.getTh() == 1) mission.setStartDate(mission.getProgram().getStartDate());
            else mission.setStartDate(mission.getProgram().getStartDate().plusDays(missionUpdateDTO.getTh() - 1).withHour(6));
            mission.setEndDate(mission.getStartDate().withHour(23).withMinute(59).withSecond(59));
        }
        if(missionUpdateDTO.getTemplate() != null)
            mission.setTemplate(missionUpdateDTO.getTemplate());
        if(missionUpdateDTO.getComments() != null)
            mission.setComments(missionUpdateDTO.getComments());
        if(missionUpdateDTO.getEssentialContentsTopic() != null) {
            if(missionUpdateDTO.getEssentialContentsTopic().equals(ContentsTopic.NULL)) {
                mission.setEssentialContentsId(null);
            } else {
                final Contents essentialContents = contentsRepository.findByTypeAndTopic(ContentsType.ESSENTIAL, missionUpdateDTO.getEssentialContentsTopic()).orElseThrow(() -> EssentialContentsNotFound.EXCEPTION);
                mission.setEssentialContentsId(essentialContents.getId());
            }
        }
        if(missionUpdateDTO.getAdditionalContentsTopic() != null) {
            if(missionUpdateDTO.getAdditionalContentsTopic().equals(ContentsTopic.NULL)) {
                mission.setAdditionalContentsId(null);
            } else {
                final Contents additionalContents = contentsRepository.findByTypeAndTopic(ContentsType.ADDITIONAL, missionUpdateDTO.getAdditionalContentsTopic()).orElseThrow(() -> AdditionalContentsNotFound.EXCEPTION);
                mission.setAdditionalContentsId(additionalContents.getId());
            }
        }
        if(missionUpdateDTO.getLimitedContentsTopic() != null) {
            if(missionUpdateDTO.getLimitedContentsTopic().equals(ContentsTopic.NULL)) {
                mission.setLimitedContentsId(null);
            } else {
                final Contents limitedContents = contentsRepository.findByTypeAndTopic(ContentsType.LIMITED, missionUpdateDTO.getLimitedContentsTopic()).orElseThrow(() -> LimitedContentsNotFound.EXCEPTION);
                mission.setLimitedContentsId(limitedContents.getId());
            }
        }

        return mission.getId();
    }

    public void deleteMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
        missionRepository.delete(mission);
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

    public List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, Long userId) {
        return missionRepository.getMissionMyDashboardList(programId, userId);
    }

    public Object getMissionMyDashboardDetail(Long missionId, MissionDashboardListStatus status, Long userId) {
        switch (status) {
            case DONE -> {
                return missionRepository.getMissionMyDashboardDoneVo(missionId, userId).orElseThrow(() -> MissionNotFound.EXCEPTION);
            }
            case YET -> {
                return missionRepository.getMissionMyDashboardYetVo(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
            }
            case ABSENT -> {
                return missionRepository.getMissionMyDashboardAbsentVo(missionId, userId).orElseThrow(() -> MissionNotFound.EXCEPTION);
            }
        }
        return missionRepository.getMissionMyDashboardYetVo(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
    }

}
