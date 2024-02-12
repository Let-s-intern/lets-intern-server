package com.letsintern.letsintern.domain.contents.mapper;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsAdminListResponse;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsIdResponse;
import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentsMapper {

    private final StringUtils stringUtils;

    public Contents toEntity(ContentsCreateDTO contentsCreateDTO, List<Long> fileIdList) {
        String fileListStr = (fileIdList == null || fileIdList.isEmpty()) ? null : stringUtils.listToString(fileIdList);
        return Contents.of(contentsCreateDTO, fileListStr);
    }

    public ContentsIdResponse toContentsIdResponse(Long contentsId) {
        return ContentsIdResponse.from(contentsId);
    }

    public ContentsAdminListResponse toContentsAdminListResponse(Page<ContentsAdminVo> contentsList) {
        return ContentsAdminListResponse.from(contentsList);
    }

}
