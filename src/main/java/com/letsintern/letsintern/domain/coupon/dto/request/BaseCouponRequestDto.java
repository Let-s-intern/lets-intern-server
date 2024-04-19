package com.letsintern.letsintern.domain.coupon.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record BaseCouponRequestDto(
        @NotNull Integer couponType,
        @NotNull List<BaseCouponProgramRequestDto> programTypeList,
        @NotNull String name,
        @NotNull String code,
        @NotNull Integer discount,
        @NotNull Integer time,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate
) {
}
