package com.letsintern.letsintern.domain.mission;

import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mission")
@Tag(name = "Mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("{programId}")
    @Operation(summary = "미션 생성하기")
    private MissionIdResponse createMission(@PathVariable Long programId, @Valid @RequestBody MissionCreateDTO missionCreateDTO) {
        return missionService.createMission(programId, missionCreateDTO);
    }
}
