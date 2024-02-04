package com.letsintern.letsintern.domain.contents.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ContentsNotFound extends BaseErrorException {

    public static final ContentsNotFound EXCEPTION = new ContentsNotFound();

    private ContentsNotFound() {
        super(ContentsErrorCode.CONTENTS_NOT_FOUND);
    }
}
