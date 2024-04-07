package com.letsintern.letsintern.domain.banner.maper;

import com.letsintern.letsintern.domain.banner.domain.LineBanner;
import com.letsintern.letsintern.domain.banner.dto.response.LineBannerListResponse;
import com.letsintern.letsintern.domain.banner.vo.LineBannerAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LineBannerMapper {

    public LineBanner toEntity(BannerCreateDTO bannerCreateDTO) {
        return LineBanner.createLineBanner(bannerCreateDTO);
    }

    public LineBannerListResponse toLineBannerListResponse(Page<LineBannerAdminVo> lineBannerAdminVos) {
        return LineBannerListResponse.of(
                (lineBannerAdminVos.hasContent()) ? lineBannerAdminVos.getContent() : new ArrayList<>(),
                PageInfo.of(lineBannerAdminVos)
        );
    }

}
