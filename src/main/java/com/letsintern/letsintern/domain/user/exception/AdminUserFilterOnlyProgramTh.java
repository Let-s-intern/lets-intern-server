package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class AdminUserFilterOnlyProgramTh extends BaseErrorException {

    public static final AdminUserFilterOnlyProgramTh EXCEPTION = new AdminUserFilterOnlyProgramTh();

    private AdminUserFilterOnlyProgramTh() {
        super(UserErrorCode.ADMIN_USER_FILTER_ONLY_TH);
    }
}
