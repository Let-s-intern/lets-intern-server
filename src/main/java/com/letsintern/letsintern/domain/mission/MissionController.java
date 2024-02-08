package com.letsintern.letsintern.domain.mission;

import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.service.MissionService;
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
    private MissionIdResponse createMission(@PathVariable Long programId, @Valid @RequestBody MissionCreateDTO missionCreateDTO) {
        return missionService.createMission(programId, missionCreateDTO);
    }

    @GetMapping("/{programId}")
    @Operation(summary = "프로그램별 미션 전체 목록")
    private MissionAdminListResponse getMissionAdminList(@PathVariable Long programId, @PageableDefault(size = 20) Pageable pageable) {
        return missionService.getMissionAdminList(programId, pageable);
    }

    @GetMapping("/{missionId}/t")
    @Operation(summary = "유저 대시보드 - 나의 기록장 미션 리스트 1개 상세 보기")
    private ResponseEntity<?> getMissionDetail(@PathVariable Long missionId, @RequestParam MissionDashboardListStatus status, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(missionService.getMissionDetail(missionId, status, principalDetails));
    }
}
