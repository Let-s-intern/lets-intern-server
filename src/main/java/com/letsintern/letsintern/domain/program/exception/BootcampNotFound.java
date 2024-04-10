package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class BootcampNotFound extends BaseErrorException {
    public static final BootcampNotFound EXCEPTION = new BootcampNotFound();

    private BootcampNotFound() {
        super(ProgramErrorCode.BOOTCAMP_NOT_FOUND);
    }
}
