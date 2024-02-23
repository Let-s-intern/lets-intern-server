package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ProgramErrorCode implements BaseErrorCode {

    PROGRAM_CREATE_BAD_REQUEST_REFUND(BAD_REQUEST, "PROGRAM_400_1", "이용료 or 보증금 프로그램 개설은 feeTotal, accountType, accountNumber, depositDueDate 정보가 모두 필요합니다"),
    PROGRAM_CREATE_BAD_REQUEST_CHALLENGE(BAD_REQUEST, "PROGRAM_400_2", "챌린지 프로그램 개설은 topic, openKakaoLink 정보가 모두 필요합니다"),
    PROGRAM_NOT_FOUND(NOT_FOUND, "PROGRAM_404_1", "존재하지 않는 프로그램입니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
