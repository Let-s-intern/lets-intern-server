package com.letsintern.letsintern.domain.banner.maper;

import com.letsintern.letsintern.domain.banner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.dto.response.MainBannerListResponse;
import com.letsintern.letsintern.domain.banner.vo.MainBannerAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MainBannerMapper {

    public MainBanner toEntity(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return MainBanner.createMainBanner(bannerCreateDTO, s3Url);
    }

    public MainBannerListResponse toMainBannerListResponse(Page<MainBannerAdminVo> mainBannerAdminVos) {
        return MainBannerListResponse.of(
                (mainBannerAdminVos.hasContent()) ? mainBannerAdminVos.getContent() : new ArrayList<>(),
                PageInfo.of(mainBannerAdminVos)
        );
    }

}
