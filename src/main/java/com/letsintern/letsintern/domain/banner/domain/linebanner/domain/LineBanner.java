package com.letsintern.letsintern.domain.banner.domain.linebanner.domain;

import com.letsintern.letsintern.domain.banner.domain.Banner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "LineBanner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LineBanner extends Banner {

    @NotNull
    private String contents;

    @NotNull
    private String colorCode;

    @Builder(access = AccessLevel.PRIVATE)
    private LineBanner(BannerCreateDTO bannerCreateDTO) {
        super(bannerCreateDTO);
        this.contents = bannerCreateDTO.contents();
        this.colorCode = bannerCreateDTO.colorCode();
    }
    public static LineBanner createLineBanner(BannerCreateDTO bannerCreateDTO) {
        return LineBanner.builder()
                .bannerCreateDTO(bannerCreateDTO)
                .build();
    }

    public void updateLineBanner(BannerUpdateDTO bannerUpdateDTO) {
        super.updateBanner(bannerUpdateDTO);
        this.contents = updateValue(this.contents, bannerUpdateDTO.contents());
        this.colorCode = updateValue(this.colorCode, bannerUpdateDTO.colorCode());
    }
}
