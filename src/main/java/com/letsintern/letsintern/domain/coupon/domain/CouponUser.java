package com.letsintern.letsintern.domain.coupon.domain;

import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class CouponUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_user_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Integer remainTime;

    public static CouponUser createCouponUser(Coupon coupon, User user) {
        CouponUser couponUser = CouponUser.builder()
                .coupon(coupon)
                .user(user)
                .remainTime(coupon.getTime())
                .build();
        coupon.addCouponUserList(couponUser);
        user.addCouponUserList(couponUser);
        return couponUser;
    }

    public void increaseRemainTime() {
        if (this.remainTime != -1)
            this.remainTime++;
    }

    public void decreaseRemainTime() {
        if (this.remainTime != -1)
            this.remainTime--;
    }
}
