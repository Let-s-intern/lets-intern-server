package com.letsintern.letsintern.domain.banner.domain.linebanner.maper;

import com.letsintern.letsintern.domain.banner.domain.linebanner.domain.LineBanner;
import com.letsintern.letsintern.domain.banner.domain.linebanner.response.LineBannerListResponse;
import com.letsintern.letsintern.domain.banner.domain.linebanner.vo.LineBannerAdminVo;
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
