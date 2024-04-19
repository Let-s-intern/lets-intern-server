package com.letsintern.letsintern.domain.coupon.dto.request;

import jakarta.validation.constraints.NotNull;

public record BaseCouponProgramRequestDto(
        @NotNull Integer programType
) {
}
