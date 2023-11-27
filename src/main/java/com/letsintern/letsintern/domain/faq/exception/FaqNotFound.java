package com.letsintern.letsintern.domain.faq.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class FaqNotFound extends BaseErrorException {

    public static final FaqNotFound EXCEPTION = new FaqNotFound();


    private FaqNotFound() {
        super(FaqErrorCode.FAQ_NOT_FOUND);
    }
}
