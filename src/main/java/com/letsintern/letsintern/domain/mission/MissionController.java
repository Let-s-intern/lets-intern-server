package com.letsintern.letsintern.domain.mission;

import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.request.MissionUpdateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminSimpleListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.service.MissionService;
import com.letsintern.letsintern.domain.mission.dto.response.MissionMyDashboardListResponse;
import com.letsintern.letsintern.domain.mission.vo.MissionAdminDetailVo;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mission")
@Tag(name = "Mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{programId}")
    @Operation(summary = "미션 생성하기")
    public MissionIdResponse createMission(@PathVariable Long programId, @Valid @RequestBody MissionCreateDTO missionCreateDTO) {
        return missionService.createMission(programId, missionCreateDTO);
    }

    @GetMapping("/{programId}/simple")
    @Operation(summary = "프로그램별 미션 전체 목록 - 미션 제출 현황")
    public MissionAdminSimpleListResponse getMissionAdminSimpleList(@PathVariable Long programId) {
        return missionService.getMissionAdminSimpleList(programId);
    }

    @GetMapping("/{programId}")
    @Operation(summary = "프로그램별 미션 전체 목록")
    public MissionAdminListResponse getMissionAdminList(@PathVariable Long programId, @PageableDefault(size = 20) Pageable pageable) {
        return missionService.getMissionAdminList(programId, pageable);
    }

    @GetMapping("/detail/{missionId}")
    @Operation(summary = "어드민 미션 1개 상세보기")
    public MissionAdminDetailVo getMissionAdmin(@PathVariable Long missionId) {
        return missionService.getMissionAdmin(missionId);
    }

    @PatchMapping("/{missionId}")
    @Operation(summary = "어드민 미션 수정")
    public MissionIdResponse updateMission(@PathVariable Long missionId, @RequestBody MissionUpdateDTO missionUpdateDTO) {
        return missionService.updateMission(missionId, missionUpdateDTO);
    }

    @DeleteMapping("/{missionId}")
    @Operation(summary = "어드민 미션 삭제")
    public void deleteMission(@PathVariable Long missionId) {
        missionService.deleteMission(missionId);
    }

    @Operation(summary = "유저 챌린지 대시보드 - 나의 기록장 미션 리스트")
    @GetMapping("/{programId}/list")
    public MissionMyDashboardListResponse getMissionMyDashboardList(@PathVariable Long programId, @RequestParam MissionDashboardListStatus status, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return missionService.getMissionMyDashboardList(programId, status, principalDetails);
    }

    @GetMapping("/{missionId}/detail")
    @Operation(summary = "유저 대시보드 - 나의 기록장 미션 리스트 1개 상세 보기")
    public ResponseEntity<?> getMissionMyDashboardDetail(@PathVariable Long missionId, @RequestParam MissionDashboardListStatus status, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(missionService.getMissionMyDashboardDetail(missionId, status, principalDetails));
    }
}
