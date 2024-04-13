package com.letsintern.letsintern.domain.program.domain.converter;

import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.global.utils.AbstractEnumCodeAttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MailStatusConverter extends AbstractEnumCodeAttributeConverter<MailStatus> {
    public MailStatusConverter() {
        super(MailStatus.class);
    }
}