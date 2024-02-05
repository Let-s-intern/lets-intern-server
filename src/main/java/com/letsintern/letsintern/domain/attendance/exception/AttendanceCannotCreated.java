package com.letsintern.letsintern.domain.attendance.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class AttendanceCannotCreated extends BaseErrorException {

    public static final AttendanceCannotCreated EXCEPTION = new AttendanceCannotCreated();

    private AttendanceCannotCreated() {
        super(AttendanceErrorCode.ATTENDANCE_CANNOT_CREATED);
    }
}
