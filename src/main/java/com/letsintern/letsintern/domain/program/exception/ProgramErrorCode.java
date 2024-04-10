package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ProgramErrorCode implements BaseErrorCode {
    CHALLENGE_NOT_FOUND(NOT_FOUND, "PROGRAM_404_1", "challenge를 찾을 수 없습니다."),

    LETS_CHAT_NOT_FOUND(NOT_FOUND, "PROGRAM_404_2", "lets-chat을 찾을 수 없습니다."),

    BOOTCAMP_NOT_FOUND(NOT_FOUND, "PROGRAM_404_3", "bootcamp를 찾을 수 없습니다."),

    PROGRAM_CREATE_BAD_REQUEST_CHALLENGE(BAD_REQUEST, "PROGRAM_400_3", "챌린지 프로그램 개설은 topic, openKakaoLink, openKakaoPassword 정보가 모두 필요합니다"),
    PROGRAM_MENTOR_PASSWORD_MISMATCH(BAD_REQUEST, "PROGRAM_400_4", "페이지 열람을 위한 비밀번호가 올바르지 않습니다"),
    PROGRAM_NOT_FOUND(NOT_FOUND, "PROGRAM_404_1", "존재하지 않는 프로그램입니다."),

    ZOOM_CREATE_INTERNAL_SERVER(INTERNAL_SERVER_ERROR, "PROGRAM_500_1", "zoom 미팅방 생성에 실패했습니다."),
    ZOOM_CREATE_TOKEN_ERROR(UNAUTHORIZED, "PROGRAM_401_1", "zoom Token 생성에 실패했습니다.")
    ;

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
