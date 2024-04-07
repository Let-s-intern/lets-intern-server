package com.letsintern.letsintern.domain.banner.domain.mainbanner.maper;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class MainBannerMapper {

    public MainBanner toEntity(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return MainBanner.createMainBanner(bannerCreateDTO, s3Url);
    }

}
