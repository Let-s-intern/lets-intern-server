package com.letsintern.letsintern.domain.banner.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum BannerErrorCode implements BaseErrorCode {

    BANNER_CREATE_NO_FILE_BAD_REQUEST(BAD_REQUEST, "BANNER_400_1", "file을 첨부해주세요"),
    LINE_BANNER_CREATE_BAD_REQUEST(BAD_REQUEST, "BANNER_400_2", "contents, colorCode, textColorCode를 입력해주세요"),
    POPUP_CREATE_BAD_REQUEST(BAD_REQUEST, "BANNER_400_3", "imgUrl을 입력해주세요"),
    BANNER_NOT_FOUND(NOT_FOUND, "BANNER_404_1", "존재하지 않는 배너 또는 팝업입니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
