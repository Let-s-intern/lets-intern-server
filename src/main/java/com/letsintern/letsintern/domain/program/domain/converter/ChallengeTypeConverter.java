package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.ChallengeType;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ChallengeTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengeType> {
    public ChallengeTypeConverter() {
        super(ChallengeType.class);
    }
}
