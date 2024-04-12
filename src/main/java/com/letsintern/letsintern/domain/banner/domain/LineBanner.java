package com.letsintern.letsintern.domain.banner.domain;

import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "LineBanner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("line_banner")
public class LineBanner extends Banner {
    @NotNull
    private String contents;

    @NotNull
    @Column(length = 7)
    private String colorCode;

    @NotNull
    @Column(length = 7)
    private String textColorCode;

    @Builder(access = AccessLevel.PRIVATE)
    private LineBanner(BannerCreateDTO bannerCreateDTO) {
        super(bannerCreateDTO);
        this.contents = bannerCreateDTO.contents();
        this.colorCode = bannerCreateDTO.colorCode();
        this.textColorCode = bannerCreateDTO.textColorCode();
    }
    public static LineBanner createLineBanner(BannerCreateDTO bannerCreateDTO) {
        return LineBanner.builder()
                .bannerCreateDTO(bannerCreateDTO)
                .build();
    }

    public void updateLineBanner(BannerUpdateDTO bannerUpdateDTO, Boolean isVisible) {
        super.updateBanner(bannerUpdateDTO, isVisible);
        this.contents = updateValue(this.contents, bannerUpdateDTO.contents());
        this.colorCode = updateValue(this.colorCode, bannerUpdateDTO.colorCode());
        this.textColorCode = updateValue(this.textColorCode, bannerUpdateDTO.textColorCode());
    }
}
