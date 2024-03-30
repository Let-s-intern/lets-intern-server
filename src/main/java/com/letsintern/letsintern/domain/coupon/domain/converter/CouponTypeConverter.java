package com.letsintern.letsintern.domain.coupon.domain.converter;

import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CouponTypeConverter extends AbstractEnumCodeAttributeConverter<CouponType> {
    public CouponTypeConverter() {
        super(CouponType.class);
    }
}
