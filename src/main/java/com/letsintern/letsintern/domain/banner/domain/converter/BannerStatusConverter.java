package com.letsintern.letsintern.domain.banner.domain.converter;

import com.letsintern.letsintern.domain.banner.domain.BannerStatus;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BannerStatusConverter extends AbstractEnumCodeAttributeConverter<BannerStatus> {
    public BannerStatusConverter() {
        super(BannerStatus.class);
    }
}
