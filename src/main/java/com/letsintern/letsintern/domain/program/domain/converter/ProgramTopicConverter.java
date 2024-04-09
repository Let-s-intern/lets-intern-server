package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.ChallengeTopic;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProgramTopicConverter extends AbstractEnumCodeAttributeConverter<ChallengeTopic> {
    public ProgramTopicConverter() {
        super(ChallengeTopic.class);
    }
}
