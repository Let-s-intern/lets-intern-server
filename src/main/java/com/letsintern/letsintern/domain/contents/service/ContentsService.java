package com.letsintern.letsintern.domain.contents.service;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsUpdateDTO;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsAdminListResponse;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsIdResponse;
import com.letsintern.letsintern.domain.contents.helper.ContentsHelper;
import com.letsintern.letsintern.domain.contents.mapper.ContentsMapper;
import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsHelper contentsHelper;
    private final ContentsMapper contentsMapper;

    @Transactional
    public ContentsIdResponse createContents(ContentsCreateDTO contentsCreateDTO, List<MultipartFile> files) throws IOException {
        return contentsMapper.toContentsIdResponse(contentsHelper.createContents(contentsCreateDTO, files));
    }

    @Transactional(readOnly = true)
    public ContentsAdminListResponse getContentsAdminList(ContentsTopic contentsTopic, Pageable pageable) {
        return contentsMapper.toContentsAdminListResponse(contentsHelper.getContentsAdminList(contentsTopic, pageable));
    }

    @Transactional(readOnly = true)
    public ContentsAdminVo getContentsDetail(Long contentsId) {
        return contentsHelper.getContentsDetail(contentsId);
    }

    @Transactional
    public ContentsIdResponse updateContents(Long contentsId, ContentsUpdateDTO contentsUpdateDTO) {
        return contentsMapper.toContentsIdResponse(contentsHelper.updateContents(contentsId, contentsUpdateDTO));
    }
}
