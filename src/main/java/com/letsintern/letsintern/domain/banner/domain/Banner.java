package com.letsintern.letsintern.domain.banner.domain;

import com.letsintern.letsintern.domain.banner.domain.converter.BannerStatusConverter;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}
