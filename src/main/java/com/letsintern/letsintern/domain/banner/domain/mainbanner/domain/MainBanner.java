package com.letsintern.letsintern.domain.banner.domain.mainbanner.domain;

import com.letsintern.letsintern.domain.banner.domain.Banner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "MainBanner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainBanner extends Banner {

    @NotNull
    private String imgUrl;

    @Builder(access = AccessLevel.PRIVATE)
    private MainBanner(BannerCreateDTO bannerCreateDTO, String s3Url) {
        super(bannerCreateDTO);
        this.imgUrl = s3Url;
    }
    public static MainBanner createMainBanner(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return MainBanner.builder()
                .bannerCreateDTO(bannerCreateDTO)
                .s3Url(s3Url)
                .build();
    }
}
