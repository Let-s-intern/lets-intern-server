package com.letsintern.letsintern.domain.banner.domain;

import com.letsintern.letsintern.domain.banner.domain.converter.BannerStatusConverter;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "Banner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String link;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Convert(converter = BannerStatusConverter.class)
    private BannerStatus status;

    private Boolean isVisible;

    public Banner(BannerCreateDTO bannerCreateDTO) {
        this.title = bannerCreateDTO.title();
        this.link = bannerCreateDTO.link();
        this.startDate = bannerCreateDTO.startDate();
        this.endDate = bannerCreateDTO.endDate();
        this.status = BannerStatus.VALID;
        this.isVisible = false;
    }

    public void updateBanner(BannerUpdateDTO bannerUpdateDTO) {
        this.title = updateValue(this.title, bannerUpdateDTO.title());
        this.link = updateValue(this.link, bannerUpdateDTO.link());
        this.startDate = updateValue(this.startDate, bannerUpdateDTO.startDate());
        this.endDate = updateValue(this.endDate, bannerUpdateDTO.endDate());
        this.isVisible = updateValue(this.isVisible, bannerUpdateDTO.isVisible());

        if(bannerUpdateDTO.endDate().isAfter(LocalDateTime.now())) {
            this.status = updateValue(this.status, BannerStatus.VALID);
        } else {
            this.status = updateValue(this.status, BannerStatus.INVALID);
        }
    }
}
