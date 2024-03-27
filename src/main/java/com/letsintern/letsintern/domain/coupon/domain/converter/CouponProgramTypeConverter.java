package com.letsintern.letsintern.domain.coupon.domain.converter;

import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CouponProgramTypeConverter extends AbstractEnumCodeAttributeConverter<CouponProgramType> {
    public CouponProgramTypeConverter() {
        super(CouponProgramType.class);
    }
}
