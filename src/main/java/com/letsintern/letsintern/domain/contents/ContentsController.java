package com.letsintern.letsintern.domain.contents;

import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsIdResponse;
import com.letsintern.letsintern.domain.contents.service.ContentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "컨텐츠 생성")
    public ContentsIdResponse createContents(@RequestPart @Valid ContentsCreateDTO contentsCreateDTO, @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {
        return contentsService.createContents(contentsCreateDTO, files);
    }
}
