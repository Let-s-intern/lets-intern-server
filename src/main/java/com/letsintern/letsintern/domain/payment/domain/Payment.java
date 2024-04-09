package com.letsintern.letsintern.domain.payment.domain;

import com.letsintern.letsintern.domain.payment.domain.coverter.FeeTypeConverter;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import com.letsintern.letsintern.global.utils.EntityUpdateValueUtils;
import com.letsintern.letsintern.global.utils.EnumValueUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;
import static com.letsintern.letsintern.global.utils.EnumValueUtils.toEntityCode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Convert(converter = FeeTypeConverter.class)
    private FeeType feeType;
    @Column(nullable = false)
    @Builder.Default
    private Integer feeRefund = 0;
    @Column(nullable = false)
    @Builder.Default
    private Integer feeCharge = 0;
    @Column(nullable = false)
    @Builder.Default
    private Integer discountValue = 0;
    private LocalDateTime feeDueDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String accountNumber;
    @OneToOne
    private Program program;

    public static Payment createPayment(PaymentRequestDto requestDto) {
        return Payment.builder()
                .feeType(toEntityCode(FeeType.class, requestDto.feeType()))
                .feeRefund(updateValue(0, requestDto.feeRefund()))
                .feeCharge(updateValue(0, requestDto.feeCharge()))
                .discountValue(updateValue(0, requestDto.discountValue()))
                .feeDueDate(requestDto.feeDueDate())
                .accountType(requestDto.accountType())
                .accountNumber(requestDto.accountNumber())
                .build();
    }
}
