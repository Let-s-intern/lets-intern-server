package com.letsintern.letsintern.domain.notice.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class NoticeNotFound extends BaseErrorException {

    public static final NoticeNotFound EXCEPTION = new NoticeNotFound();

    private NoticeNotFound() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND);
    }
}
