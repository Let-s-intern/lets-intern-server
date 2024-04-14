package com.letsintern.letsintern.domain.onlineprogram;

import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramUpdateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramAdminListResponse;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramAdminResponse;
import com.letsintern.letsintern.domain.onlineprogram.service.OnlineProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/online-program")
@RequiredArgsConstructor
@Tag(name = "OnlineProgram")
public class OnlineProgramController {

    private final OnlineProgramService onlineProgramService;

    @PostMapping
    @Operation(summary = "어드민 상시 컨텐츠 생성")
    public void createOnlineProgramForAdmin(@RequestPart @Valid final OnlineProgramCreateDTO onlineProgramCreateDTO,
                                            @RequestParam("file") MultipartFile file) {
        onlineProgramService.createOnlineProgram(onlineProgramCreateDTO, file);
    }

    @GetMapping("/admin")
    @Operation(summary = "어드민 상시 컨텐츠 전체 목록")
    public ResponseEntity<OnlineProgramAdminListResponse> getOnlineProgramListForAdmin(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(onlineProgramService.getOnlineProgramListForAdmin(pageable));
    }

    @GetMapping("/admin/{id}")
    @Operation(summary = "어드민 상시 컨텐츠 1건 상세")
    public ResponseEntity<OnlineProgramAdminResponse> getOnlineProgramForAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(onlineProgramService.getOnlineProgramForAdmin(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "어드민 상시 컨텐츠 1건 수정")
    public void updateOnlineProgramForAdmin(@PathVariable Long id,
                                            @RequestPart(required = false) final OnlineProgramUpdateDTO onlineProgramUpdateDTO,
                                            @RequestParam(value = "file", required = false) MultipartFile file) {
        onlineProgramService.updateOnlineProgram(id, onlineProgramUpdateDTO, file);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "어드민 상시 컨텐츠 1건 삭제")
    public void deleteOnlineProgramForAdmin(@PathVariable Long id) {
        onlineProgramService.deleteOnlineProgram(id);
    }
}
