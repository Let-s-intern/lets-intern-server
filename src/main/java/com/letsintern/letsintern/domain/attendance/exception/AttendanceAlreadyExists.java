package com.letsintern.letsintern.domain.attendance.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class AttendanceAlreadyExists extends BaseErrorException {

    public static final AttendanceAlreadyExists EXCEPTION = new AttendanceAlreadyExists();

    public AttendanceAlreadyExists() {
        super(AttendanceErrorCode.ATTENDANCE_ALREADY_EXISTS);
    }
}
