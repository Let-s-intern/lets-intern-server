package com.letsintern.letsintern.domain.coupon.domain;

import com.letsintern.letsintern.domain.coupon.domain.converter.CouponProgramTypeConverter;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponProgramEnumVo;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class CouponProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_program_id")
    private Long id;
    @Convert(converter = CouponProgramTypeConverter.class)
    private CouponProgramType couponProgramType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public static CouponProgram createCouponProgram(BaseCouponProgramEnumVo baseCouponProgramEnumVo, Coupon coupon) {
        CouponProgram couponProgram = CouponProgram.builder()
                .couponProgramType(baseCouponProgramEnumVo.couponProgramType())
                .coupon(coupon)
                .build();
        coupon.addCouponProgramType(couponProgram);
        return couponProgram;
    }
}
