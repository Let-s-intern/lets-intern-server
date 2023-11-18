package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.AdminProgramListDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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

    @Operation(summary = "프로그램 목록 (전체, 타입 - CHALLENGE, BOOTCAMP, LETS_CHAT)")
    @GetMapping("")
    public ProgramListDTO getProgramThumbnailList(
            @RequestParam(required = false) String type,
            @PageableDefault(size = 20) Pageable pageable) {
        return programService.getProgramThumbnailList(type, pageable);
    }

    @Operation(summary = "프로그램 1개 상세 보기")
    @GetMapping("/{programId}")
    public ProgramDetailDTO getProgramDetailVo(@PathVariable Long programId) {
        return programService.getProgramDetailDTO(programId);
    }

    @Operation(summary = "어드민 프로그램 목록 (전체, 타입, 타입&기수)")
    @GetMapping("/admin")
    public AdminProgramListDTO getAdminProgramList(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer th,
            @PageableDefault(size = 20) Pageable pageable) {
        return programService.getProgramAdminList(type, th, pageable);
    }

    @Operation(summary = "어드민 프로그램 1개 상세 보기")
    @GetMapping("/admin/{programId}")
    public Program getProgram(@PathVariable Long programId) {
        return programService.getProgram(programId);
    }

    @Operation(summary = "어드민 프로그램 신규 개설")
    @PostMapping("")
    public ProgramIdResponseDTO createProgram(
            @RequestBody ProgramCreateRequestDTO programCreateRequestDTO) {
        return programService.createProgram(programCreateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 수정")
    @PatchMapping("/{programId}")
    public ProgramIdResponseDTO updateProgram(@PathVariable Long programId, @RequestBody ProgramUpdateRequestDTO programUpdateRequestDTO) throws ParseException {
        return programService.updateProgram(programId, programUpdateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 삭제")
    @DeleteMapping("/{programId}")
    public ResponseEntity<?> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);
        return ResponseEntity.ok(null);
    }

}
