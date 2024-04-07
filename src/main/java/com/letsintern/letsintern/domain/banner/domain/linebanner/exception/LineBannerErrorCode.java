package com.letsintern.letsintern.domain.banner.domain.linebanner.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum LineBannerErrorCode implements BaseErrorCode {

    LINE_BANNER_CREATE_BAD_REQUEST(BAD_REQUEST, "LINE_BANNER_400_1", "contents, colorCode를 입력해주세요");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
