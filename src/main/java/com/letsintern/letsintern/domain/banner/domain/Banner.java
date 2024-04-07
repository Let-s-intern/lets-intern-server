package com.letsintern.letsintern.domain.banner.domain;

import com.letsintern.letsintern.domain.banner.domain.converter.BannerStatusConverter;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "Banner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Banner {

    @Id
    @Column(name = "banner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String link;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Convert(converter = BannerStatusConverter.class)
    private BannerStatus status;

    @NotNull
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
