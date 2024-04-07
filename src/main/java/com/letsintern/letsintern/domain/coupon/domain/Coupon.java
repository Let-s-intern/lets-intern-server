package com.letsintern.letsintern.domain.coupon.domain;

import com.letsintern.letsintern.domain.coupon.domain.converter.CouponProgramTypeConverter;
import com.letsintern.letsintern.domain.coupon.domain.converter.CouponTypeConverter;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponEnumVo;
import com.letsintern.letsintern.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;
    private String name;
    private String code;
    private Integer discount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer time;
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CouponUser> couponUserList = new ArrayList<>();
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CouponProgram> couponProgramList = new ArrayList<>();

    public static Coupon createCoupon(BaseCouponEnumVo baseCouponEnumVo) {
        return Coupon.builder()
                .couponType(baseCouponEnumVo.couponType())
                .name(baseCouponEnumVo.name())
                .code(baseCouponEnumVo.code())
                .discount(baseCouponEnumVo.discount())
                .startDate(baseCouponEnumVo.startDate())
                .endDate(baseCouponEnumVo.endDate())
                .time(baseCouponEnumVo.time())
                .build();
    }

    public void updateCoupon(BaseCouponEnumVo baseCouponEnumVo) {
        this.couponType = updateValue(this.couponType, baseCouponEnumVo.couponType());
        this.name = updateValue(this.name, baseCouponEnumVo.name());
        this.code = updateValue(this.code, baseCouponEnumVo.code());
        this.discount = updateValue(this.discount, baseCouponEnumVo.discount());
        this.startDate = updateValue(this.startDate, baseCouponEnumVo.startDate());
        this.endDate = updateValue(this.endDate, baseCouponEnumVo.endDate());
        this.time = updateValue(this.time, baseCouponEnumVo.time());
    }

    public void addCouponUserList(CouponUser couponUser) {
        this.couponUserList.add(couponUser);
    }

    public void addCouponProgramType(CouponProgram couponProgram) {
        this.couponProgramList.add(couponProgram);
    }

    public void resetCouponProgramType() {
        this.couponProgramList = new ArrayList<>();
    }
}
