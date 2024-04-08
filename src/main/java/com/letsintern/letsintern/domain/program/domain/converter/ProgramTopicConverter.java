package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.ProgramTopic;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProgramTopicConverter extends AbstractEnumCodeAttributeConverter<ProgramTopic> {
    public ProgramTopicConverter() {
        super(ProgramTopic.class);
    }
}
