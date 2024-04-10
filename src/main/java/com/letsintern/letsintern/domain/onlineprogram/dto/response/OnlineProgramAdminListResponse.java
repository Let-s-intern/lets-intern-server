package com.letsintern.letsintern.domain.onlineprogram.dto.response;

import com.letsintern.letsintern.domain.onlineprogram.vo.OnlineProgramAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record OnlineProgramAdminListResponse(
        List<OnlineProgramAdminVo> onlineProgramList,
        PageInfo pageInfo
) {
    public static OnlineProgramAdminListResponse of(List<OnlineProgramAdminVo> onlineProgramList, PageInfo pageInfo) {
        return OnlineProgramAdminListResponse.builder()
                .onlineProgramList(onlineProgramList)
                .pageInfo(pageInfo)
                .build();
    }
}
