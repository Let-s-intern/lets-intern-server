package com.letsintern.letsintern.domain.contents.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class LimitedContentsNotFound extends BaseErrorException {

    public static final LimitedContentsNotFound EXCEPTION = new LimitedContentsNotFound();

    private LimitedContentsNotFound() {
        super(ContentsErrorCode.LIMITED_CONTENTS_NOT_FOUND);
    }
}
