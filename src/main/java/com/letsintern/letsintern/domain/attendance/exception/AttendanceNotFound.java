package com.letsintern.letsintern.domain.attendance.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class AttendanceNotFound extends BaseErrorException {

    public static final AttendanceNotFound EXCEPTION = new AttendanceNotFound();

    private AttendanceNotFound() {
        super(AttendanceErrorCode.ATTENDANCE_NOT_FOUND);
    }
}
