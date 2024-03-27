package com.letsintern.letsintern.domain.coupon;

import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponApplyResponseDto;
import com.letsintern.letsintern.domain.coupon.service.CouponService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Coupon")
@RequiredArgsConstructor
@RequestMapping("/coupon")
@RestController
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/{code}")
    public ResponseEntity<CouponApplyResponseDto> getAvailableCoupon(@AuthenticationPrincipal final PrincipalDetails principalDetails,
                                                                     @PathVariable("code") final String code) {
        final CouponApplyResponseDto responseDto = couponService.getAvailableCoupon(principalDetails, code);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public void createNewCoupon(@RequestBody final BaseCouponRequestDto baseCouponRequestDto) {
        couponService.createNewCoupon(baseCouponRequestDto);
    }

    @PatchMapping("/{id}")
    public void updateCouponInfo(@PathVariable("id") final Long couponId,
                                 @RequestBody final BaseCouponRequestDto baseCouponRequestDto) {
        couponService.updateCouponInfo(couponId, baseCouponRequestDto);
    }
}
