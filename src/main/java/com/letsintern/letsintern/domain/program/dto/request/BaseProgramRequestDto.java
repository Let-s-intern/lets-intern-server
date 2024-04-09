package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;

import java.time.LocalDateTime;
import java.util.List;

public record BaseProgramRequestDto(
        ProgramRequestDto programInfo,
        PaymentRequestDto paymentInfo,
        ChallengeRequestDto challengeInfo
        // Bootcamp, 렛츠챗도 여기 넣어주세요
) {
}
