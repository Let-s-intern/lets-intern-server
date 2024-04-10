package com.letsintern.letsintern.domain.onlineprogram.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum OnlineProgramErrorCode implements BaseErrorCode {

    ONLINE_PROGRAM_NOT_FOUND(NOT_FOUND, "ONLINE_PROGRAM_404_1", "존재하지 않는 상시 컨텐츠입니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
