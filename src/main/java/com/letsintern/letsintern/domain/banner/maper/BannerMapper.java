package com.letsintern.letsintern.domain.banner.maper;

import com.letsintern.letsintern.domain.banner.domain.LineBanner;
import com.letsintern.letsintern.domain.banner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.domain.Popup;
import com.letsintern.letsintern.domain.banner.domain.ProgramBanner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerListResponseDto;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BannerMapper {

    public LineBanner toLineBannerEntity(BannerCreateDTO bannerCreateDTO) {
        return LineBanner.createLineBanner(bannerCreateDTO);
    }

    public MainBanner toMainBannerEntity(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return MainBanner.createMainBanner(bannerCreateDTO, s3Url);
    }

    public Popup toPopupEntity(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return Popup.createPopup(bannerCreateDTO, s3Url);
    }

    public ProgramBanner toProgramBannerEntity(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return ProgramBanner.createProgramBanner(bannerCreateDTO, s3Url);
    }

    public BannerIdResponse toBannerIdResponse(Long bannerId) {
        return BannerIdResponse.from(bannerId);
    }

    public BannerAdminListResponse toBannerAdminListResponse(Page<?> bannerAdminVos) {
        return BannerAdminListResponse.of(
                (bannerAdminVos.hasContent()) ? bannerAdminVos.getContent() : new ArrayList<>(),
                PageInfo.of(bannerAdminVos)
        );
    }

    public <T> BannerAdminResponse<?> toBannerAdminResponse(T banner) {
        return BannerAdminResponse.from(banner);
    }
  
    public BannerListResponseDto<?> toBannerListResponseDto(Page<?> bannerVoPage) {
        return BannerListResponseDto.of(
                bannerVoPage.hasContent() ? bannerVoPage.getContent() : new ArrayList<>(),
                PageInfo.of(bannerVoPage)
        );
    }
}
