package com.letsintern.letsintern.domain.banner.domain.popup.response;

import com.letsintern.letsintern.domain.banner.domain.popup.vo.PopupAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record PopupListResponse(List<PopupAdminVo> popupList, PageInfo pageInfo) {
    public static PopupListResponse of(List<PopupAdminVo> popupAdminVoList, PageInfo pageInfo) {
        return PopupListResponse.builder()
                .popupList(popupAdminVoList)
                .pageInfo(pageInfo)
                .build();
    }
}
