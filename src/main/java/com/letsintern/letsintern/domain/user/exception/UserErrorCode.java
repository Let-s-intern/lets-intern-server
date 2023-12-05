package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "존재하지 않는 유저입니다."),
    DUPLICATE_USER(BAD_REQUEST, "USER_400_1", "이미 존재하는 유저입니다."),
    MISMATCH_PASSWORD(BAD_REQUEST, "USER_400_2", "비밀번호가 일치하지 않습니다"),
    MYPAGE_MISMATCH_PASSWORD(BAD_REQUEST, "USER_400_3", "기존 비밀번호가 일치하지 않습니다"),
    NOT_REFRESH_TOKEN(BAD_REQUEST, "ADMIN_400_3", "리프레시 토큰이 아닙니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "ADMIN_404_2", "존재하지 않거나 만료된 리프레시 토큰입니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
