package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramTotalListDTO;
import com.letsintern.letsintern.domain.program.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
@Tag(name = "Program")
public class ProgramController {

    private final ProgramService programService;

    @Operation(summary = "프로그램 신규 개설")
    @PostMapping("/create")
    public ProgramIdResponseDTO createProgram(@RequestBody ProgramCreateRequestDTO programCreateRequestDTO) {
        return programService.createProgram(programCreateRequestDTO);
    }

    @Operation(summary = "프로그램 전체 목록")
    @GetMapping("/list")
    public ProgramTotalListDTO getProgramTotalList() {
        return programService.getProgramTotalList();
    }

}
