package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProgramTypeConverter extends AbstractEnumCodeAttributeConverter<ProgramType> {
    public ProgramTypeConverter() {
        super(ProgramType.class);
    }
}
