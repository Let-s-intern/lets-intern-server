package com.letsintern.letsintern.domain.contents.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class AdditionalContentsNotFound extends BaseErrorException {

    public static final AdditionalContentsNotFound EXCEPTION = new AdditionalContentsNotFound();

    private AdditionalContentsNotFound() {
        super(ContentsErrorCode.ADDITIONAL_CONTENTS_NOT_FOUND);
    }
}
