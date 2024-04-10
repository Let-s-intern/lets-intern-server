package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;

public record BaseProgramRequestDto(
        ProgramRequestDto programInfo,
        PaymentRequestDto paymentInfo,
        ChallengeRequestDto challengeInfo,
        LetsChatRequestDto letsCHatRequestDto,
        BootcampRequestDto bootcampRequestDto
) {
}
