package com.letsintern.letsintern.domain.banner.domain.mainbanner.dto.response;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.vo.MainBannerAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MainBannerListResponse(List<MainBannerAdminVo> mainBannerList, PageInfo pageInfo) {
    public static MainBannerListResponse of(List<MainBannerAdminVo> mainBannerAdminVoList, PageInfo pageInfo) {
        return MainBannerListResponse.builder()
                .mainBannerList(mainBannerAdminVoList)
                .pageInfo(pageInfo)
                .build();
    }
}
