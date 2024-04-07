package com.letsintern.letsintern.domain.banner.domain.popup.domain;

import com.letsintern.letsintern.domain.banner.domain.Banner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "Popup")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Popup extends Banner {

    @NotNull
    private String imgUrl;

    @Builder(access = AccessLevel.PRIVATE)
    private Popup(BannerCreateDTO bannerCreateDTO) {
        super(bannerCreateDTO);
        this.imgUrl = bannerCreateDTO.imgUrl();
    }

    public static Popup createPopup(BannerCreateDTO bannerCreateDTO) {
        return Popup.builder()
                .bannerCreateDTO(bannerCreateDTO)
                .build();
    }

    public void updatePopup(BannerUpdateDTO bannerUpdateDTO) {
        super.updateBanner(bannerUpdateDTO);
        this.imgUrl = updateValue(this.imgUrl, bannerUpdateDTO.imgUrl());
    }
}
