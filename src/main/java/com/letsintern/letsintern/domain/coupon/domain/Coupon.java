package com.letsintern.letsintern.domain.coupon.domain;

import com.letsintern.letsintern.domain.coupon.domain.converter.CouponProgramTypeConverter;
import com.letsintern.letsintern.domain.coupon.domain.converter.CouponTypeConverter;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponEnumVo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;
    @Convert(converter = CouponProgramTypeConverter.class)
    private CouponProgramType couponProgramType;
    private String name;
    private String code;
    private Integer discount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer time;
    @OneToMany(mappedBy = "coupon")
    @Builder.Default
    private List<CouponUser> couponUserList = new ArrayList<>();

    public static Coupon createCoupon(BaseCouponEnumVo baseCouponEnumVo) {
        return Coupon.builder()
                .couponType(baseCouponEnumVo.couponType())
                .couponProgramType(baseCouponEnumVo.couponProgramType())
                .name(baseCouponEnumVo.name())
                .code(baseCouponEnumVo.name())
                .discount(baseCouponEnumVo.discount())
                .startDate(baseCouponEnumVo.startDate())
                .endDate(baseCouponEnumVo.endDate())
                .time(baseCouponEnumVo.time())
                .build();
    }

    public void updateCoupon(BaseCouponEnumVo baseCouponEnumVo) {
        this.couponType = baseCouponEnumVo.couponType();
        this.couponProgramType = baseCouponEnumVo.couponProgramType();
        this.name = baseCouponEnumVo.name();
        this.code = baseCouponEnumVo.code();
        this.discount = baseCouponEnumVo.discount();
        this.startDate = baseCouponEnumVo.startDate();
        this.endDate = baseCouponEnumVo.endDate();
        this.time = baseCouponEnumVo.time();
    }
}
