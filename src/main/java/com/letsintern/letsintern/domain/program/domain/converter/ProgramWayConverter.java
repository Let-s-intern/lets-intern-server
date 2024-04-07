package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProgramWayConverter extends AbstractEnumCodeAttributeConverter<ProgramWay> {
    public ProgramWayConverter() {
        super(ProgramWay.class);
    }
}
