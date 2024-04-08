package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProgramStatusConverter extends AbstractEnumCodeAttributeConverter<ProgramStatus> {
    public ProgramStatusConverter() {
        super(ProgramStatus.class);
    }
}
