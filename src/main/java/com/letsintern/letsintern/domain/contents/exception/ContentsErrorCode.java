package com.letsintern.letsintern.domain.contents.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ContentsErrorCode implements BaseErrorCode {

    CONTENTS_NOT_FOUND(NOT_FOUND, "CONTENTS_404_1", "존재하지 않는 컨텐츠입니다."),
    ESSENTIAL_CONTENTS_NOT_FOUND(NOT_FOUND, "CONTENTS_404_2", "존재하지 않는 필수 컨텐츠입니다."),
    ADDITIONAL_CONTENTS_NOT_FOUND(NOT_FOUND, "CONTENTS_404_3", "존재하지 않는 추가 컨텐츠입니다."),
    LIMITED_CONTENTS_NOT_FOUND(NOT_FOUND, "CONTENTS_404_4", "존재하지 않는 제한 컨텐츠입니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
