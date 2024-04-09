package com.letsintern.letsintern.domain.payment.domain.coverter;

import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FeeTypeConverter extends AbstractEnumCodeAttributeConverter<FeeType> {
    public FeeTypeConverter() {
        super(FeeType.class);
    }
}
