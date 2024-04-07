package com.letsintern.letsintern.domain.banner.maper;

import com.letsintern.letsintern.domain.banner.domain.ProgramBanner;
import com.letsintern.letsintern.domain.banner.dto.response.ProgramBannerListResponse;
import com.letsintern.letsintern.domain.banner.vo.ProgramBannerAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProgramBannerMapper {

    public ProgramBanner toEntity(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return ProgramBanner.createProgramBanner(bannerCreateDTO, s3Url);
    }

    public ProgramBannerListResponse toProgramBannerListResponse(Page<ProgramBannerAdminVo> programBannerAdminVos) {
        return ProgramBannerListResponse.of(
                (programBannerAdminVos.hasContent()) ? programBannerAdminVos.getContent() : new ArrayList<>(),
                PageInfo.of(programBannerAdminVos)
        );
    }

}
