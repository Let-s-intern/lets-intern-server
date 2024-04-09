package com.letsintern.letsintern.domain.payment.domail;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private ProgramFeeType feeType;
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
}
