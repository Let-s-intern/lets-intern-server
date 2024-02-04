package com.letsintern.letsintern.domain.contents.dto.response;

import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ContentsAdminListResponse {

    private List<ContentsAdminVo> contentsList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private ContentsAdminListResponse(Page<ContentsAdminVo> contentsList) {
        if(contentsList.hasContent()) this.contentsList = contentsList.getContent();
        this.pageInfo = PageInfo.of(contentsList);
    }

    public static ContentsAdminListResponse from(Page<ContentsAdminVo> contentsList) {
        return ContentsAdminListResponse.builder()
                .contentsList(contentsList)
                .build();
    }
}
