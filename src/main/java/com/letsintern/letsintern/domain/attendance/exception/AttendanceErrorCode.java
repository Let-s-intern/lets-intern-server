package com.letsintern.letsintern.domain.attendance.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum AttendanceErrorCode implements BaseErrorCode {

    ATTENDANCE_NOT_FOUND(NOT_FOUND, "ATTENDANCE_404_1", "존재하지 않는 출석입니다."),
    ATTENDANCE_CANNOT_CREATED(BAD_REQUEST, "ATTENDANCE_400_1", "출석 인증이 불가능합니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
