package com.letsintern.letsintern.domain.onlineprogram;

import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramIdResponse;
import com.letsintern.letsintern.domain.onlineprogram.service.OnlineProgramService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/online-program")
@RequiredArgsConstructor
@Tag(name = "OnlineProgram")
public class OnlineProgramController {

    private final OnlineProgramService onlineProgramService;

    @PostMapping
    public OnlineProgramIdResponse createOnlineProgramForAdmin(@RequestBody @Valid OnlineProgramCreateDTO onlineProgramCreateDTO) {
        return onlineProgramService.createOnlineProgram(onlineProgramCreateDTO);
    }
}
