package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ApplicationErrorCode implements BaseErrorCode {

    DUPLICATE_APPLICATION(BAD_REQUEST, "APPLICATION_400_1", "사전에 신청한 내역이 존재합니다."),
    APPLICATION_GUEST_BAD_REQUEST(BAD_REQUEST, "APPLICATION_400_2", "비회원 신청은 이름, 이메일, 전화번호 정보가 모두 필요합니다."),
    APPLICATION_USER_BAD_REQUEST(BAD_REQUEST, "APPLICATION_400_3", "학교, 전공 정보를 입력해야 신청이 가능합니다."),
    APPLICATION_CANNOT_DELETED(BAD_REQUEST, "APPLICATION_400_4", "지원 취소 기간이 아닙니다."),
    APPLICATION_UNAUTHORIZED(UNAUTHORIZED, "APPLICATION_401_1", "수정 권한이 없습니다"),
    APPLICATION_NOT_FOUND(NOT_FOUND, "APPLICATION_404_1", "존재하지 않는 신청 내역입니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
