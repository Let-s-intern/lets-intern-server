package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum CouponErrorCode implements BaseErrorCode {
    INVALID_COUPON_PROGRAM_TYPE(BAD_REQUEST, "COUPON_400_1", "프로그램에 적용할 수 없는 쿠폰입니다."),

    COUPON_NOT_FOUND(NOT_FOUND, "COUPON_404_1", "저장된 쿠폰을 찾을 수 없습니다."),
    COUPON_HISTORY_NOT_FOUND(NOT_FOUND, "COUPON_404_2", "쿠폰 사용내역을 찾을 수 없습니다."),

    COUPON_BEFORE_TIME(CONFLICT, "COUPON_409_1", "아직 사용할 수 없는 쿠폰입니다."),
    COUPON_EXPIRED(CONFLICT, "COUPON_409_2", "기간이 만료된 쿠폰입니다."),
    COUPON_USAGE_LIMIT_EXCEEDED(CONFLICT, "COUPON_409_3", "더이상 사용할 수 없는 쿠폰입니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
