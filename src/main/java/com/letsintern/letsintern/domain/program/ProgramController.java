package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramTotalListDTO;
import com.letsintern.letsintern.domain.program.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;

    @PostMapping("/create")
    public ProgramIdResponseDTO createProgram(@RequestBody ProgramCreateRequestDTO programCreateRequestDTO) {
        return programService.createProgram(programCreateRequestDTO);
    }

    @GetMapping("/list")
    public ProgramTotalListDTO getProgramTotalList() {
        return programService.getProgramTotalList();
    }

}
