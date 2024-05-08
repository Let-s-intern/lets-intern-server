package com.letsintern.letsintern.domain.attendance.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class AttendanceCannotUpdated extends BaseErrorException {

    public static final AttendanceCannotUpdated EXCEPTION = new AttendanceCannotUpdated();

    private AttendanceCannotUpdated() {
        super(AttendanceErrorCode.ATTENDANCE_CANNOT_UPDATED);
    }
}
