package com.letsintern.letsintern.domain.contents.service;

import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsIdResponse;
import com.letsintern.letsintern.domain.contents.helper.ContentsHelper;
import com.letsintern.letsintern.domain.contents.mapper.ContentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsHelper contentsHelper;
    private final ContentsMapper contentsMapper;

    public ContentsIdResponse createContents(ContentsCreateDTO contentsCreateDTO, List<MultipartFile> files) throws IOException {
        return contentsMapper.toContentsIdResponse(contentsHelper.createContents(contentsCreateDTO, files));
    }
}
