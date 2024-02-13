package com.letsintern.letsintern.domain.contents;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsUpdateDTO;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsAdminListResponse;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsIdResponse;
import com.letsintern.letsintern.domain.contents.service.ContentsService;
import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
@Tag(name = "Contents")
public class ContentsController {

    private final ContentsService contentsService;

    @PostMapping("")
    @Operation(summary = "어드민 컨텐츠 생성")
    public ContentsIdResponse createContents(@RequestPart @Valid ContentsCreateDTO contentsCreateDTO, @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {
        return contentsService.createContents(contentsCreateDTO, files);
    }

    @GetMapping("")
    @Operation(summary = "어드민 컨텐츠 목록 보기")
    private ContentsAdminListResponse getContentsAdminList(@RequestParam(required = false) ContentsTopic contentsTopic, @PageableDefault(size = 20) Pageable pageable) {
        return contentsService.getContentsAdminList(contentsTopic, pageable);
    }

    @GetMapping("/{contentsId}")
    @Operation(summary = "어드민 컨텐츠 1개 상세 보기")
    private ContentsAdminVo getContentsDetail(@PathVariable Long contentsId) {
        return contentsService.getContentsDetail(contentsId);
    }

    @PatchMapping("/{contentsId}")
    @Operation(summary = "어드민 컨텐츠 수정")
    private ContentsIdResponse updateContents(@PathVariable Long contentsId, @RequestBody ContentsUpdateDTO contentsUpdateDTO) {
        return contentsService.updateContents(contentsId, contentsUpdateDTO);
    }

    @DeleteMapping("/{contentsId}")
    @Operation(summary = "어드민 컨텐츠 삭제")
    private void deleteContents(@PathVariable Long contentsId) {
        contentsService.deleteContents(contentsId);
    }
}
