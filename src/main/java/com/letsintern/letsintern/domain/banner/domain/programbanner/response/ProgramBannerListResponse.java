package com.letsintern.letsintern.domain.banner.domain.programbanner.response;

import com.letsintern.letsintern.domain.banner.domain.programbanner.vo.ProgramBannerAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramBannerListResponse(List<ProgramBannerAdminVo> programBannerList, PageInfo pageInfo) {
    public static ProgramBannerListResponse of(List<ProgramBannerAdminVo> programBannerAdminVoList, PageInfo pageInfo) {
        return ProgramBannerListResponse.builder()
                .programBannerList(programBannerAdminVoList)
                .pageInfo(pageInfo)
                .build();
    }
}
