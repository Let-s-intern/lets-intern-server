package com.letsintern.letsintern.domain.contents.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class EssentialContentsNotFound extends BaseErrorException {

    public static final EssentialContentsNotFound EXCEPTION = new EssentialContentsNotFound();

    private EssentialContentsNotFound() {
        super(ContentsErrorCode.ESSENTIAL_CONTENTS_NOT_FOUND);
    }
}
