package com.letsintern.letsintern.domain.contents.mapper;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import com.letsintern.letsintern.domain.contents.dto.response.ContentsIdResponse;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentsMapper {

    private final StringUtils stringUtils;

    public Contents toEntity(ContentsCreateDTO contentsCreateDTO, List<Long> fileIdList) {
        String fileListStr = (fileIdList.size() > 0) ? stringUtils.listToString(fileIdList) : null;
        return Contents.of(contentsCreateDTO, fileListStr);
    }

    public ContentsIdResponse toContentsIdResponse(Long contentsId) {
        return ContentsIdResponse.from(contentsId);
    }

}
