package com.letsintern.letsintern.domain.banner.domain;

import com.letsintern.letsintern.domain.banner.domain.converter.BannerStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Banner")
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String title;

    private String link;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder.Default
    @Convert(converter = BannerStatusConverter.class)
    private BannerStatus status = BannerStatus.VALID;

    @Builder.Default
    private Boolean isVisible = false;
}
