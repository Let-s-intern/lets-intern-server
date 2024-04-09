package com.letsintern.letsintern.domain.payment.repository;

import com.letsintern.letsintern.domain.payment.domail.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentQueryRepository {
}
