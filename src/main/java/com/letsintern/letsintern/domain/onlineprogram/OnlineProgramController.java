package com.letsintern.letsintern.domain.onlineprogram;

import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramUpdateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramAdminListResponse;
import com.letsintern.letsintern.domain.onlineprogram.service.OnlineProgramService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/online-program")
@RequiredArgsConstructor
@Tag(name = "OnlineProgram")
public class OnlineProgramController {

    private final OnlineProgramService onlineProgramService;

    @PostMapping
    public void createOnlineProgramForAdmin(@RequestPart @Valid final OnlineProgramCreateDTO onlineProgramCreateDTO,
                                            @RequestParam("file")MultipartFile file) {
        onlineProgramService.createOnlineProgram(onlineProgramCreateDTO, file);
    }

    @GetMapping("/admin")
    public OnlineProgramAdminListResponse getOnlineProgramListForAdmin(@PageableDefault Pageable pageable) {
        return onlineProgramService.getOnlineProgramListForAdmin(pageable);
    }

    @PatchMapping("/{id}")
    public void updateOnlineProgramForAdmin(@PathVariable Long id,
                                            @RequestPart(required = false) final OnlineProgramUpdateDTO onlineProgramUpdateDTO,
                                            @RequestParam(value = "file", required = false) MultipartFile file) {
        onlineProgramService.updateOnlineProgram(id, onlineProgramUpdateDTO, file);
    }

    @DeleteMapping("/{id}")
    public void deleteOnlineProgramForAdmin(@PathVariable Long id) {
        onlineProgramService.deleteOnlineProgram(id);
    }
}
