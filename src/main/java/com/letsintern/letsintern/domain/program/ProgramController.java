package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
@Tag(name = "Program")
public class ProgramController {

    private final ProgramService programService;

    @Operation(summary = "어드민 프로그램 신규 개설")
    @PostMapping("")
    public ProgramIdResponseDTO createProgram(
            @RequestBody ProgramCreateRequestDTO programCreateRequestDTO) {
        // @RequestPart List<MultipartFile> files 추가 필요
        return programService.createProgram(programCreateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 수정")
    @PatchMapping("/{programId}")
    public ProgramIdResponseDTO updateProgram(@PathVariable Long programId, @RequestBody ProgramUpdateRequestDTO programUpdateRequestDTO) {
        return programService.updateProgram(programId, programUpdateRequestDTO);
    }

    @Operation(summary = "프로그램 전체 목록")
    @GetMapping("")
    public ProgramListDTO getProgramList(@PageableDefault(size = 15) Pageable pageable) {
        return programService.getProgramList(pageable);
    }

    @Operation(summary = "타입 별 프로그램 목록 (CHALLENGE_HALF/FULL, BOOTCAMP, LETS_CHAT)")
    @GetMapping("/{type}")
    public ProgramListDTO getProgramTypeList(@PathVariable String type, @PageableDefault(size = 15) Pageable pageable) {
        return programService.getProgramTypeList(type, pageable);
    }

}
