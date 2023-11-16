package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.service.ProgramService;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
@Tag(name = "Program")
public class ProgramController {

    private final ProgramService programService;

    @Operation(summary = "AWS Target Group 상태 확인용")
    @GetMapping("/tg")
    public ResponseEntity<String> targetGroup() {
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "어드민 프로그램 신규 개설")
    @PostMapping("")
    public ProgramIdResponseDTO createProgram(
            @RequestBody ProgramCreateRequestDTO programCreateRequestDTO) {
        return programService.createProgram(programCreateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 수정")
    @PatchMapping("/{programId}")
    public ProgramIdResponseDTO updateProgram(@PathVariable Long programId, @RequestBody ProgramUpdateRequestDTO programUpdateRequestDTO) {
        return programService.updateProgram(programId, programUpdateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 삭제")
    @DeleteMapping("/{programId}")
    public ResponseEntity<?> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "프로그램 전체 목록, 타입별 프로그램 목록 (CHALLENGE, BOOTCAMP, LETS_CHAT)")
    @GetMapping("")
    public ResponseEntity<?> getProgramList(@RequestParam(required = false) String type, @RequestParam(required = false) boolean isAdmin, @PageableDefault(size = 20) Pageable pageable) {
        if(type != null) return ResponseEntity.ok(programService.getProgramTypeList(type, pageable));
        if(isAdmin) return ResponseEntity.ok(programService.getProgramAdminList(pageable));
        return ResponseEntity.ok(programService.getProgramList(pageable));
    }

    @Operation(summary = "프로그램 1개 상세 보기")
    @GetMapping("/{programId}")
    public ResponseEntity<?> getProgramDetail(@PathVariable Long programId, @RequestParam(required = false) boolean isAdmin) {
        if(Objects.equals(isAdmin, true)) return ResponseEntity.ok(programService.getProgram(programId));
        return ResponseEntity.ok(programService.getProgramDetailDTO(programId));
    }


}
