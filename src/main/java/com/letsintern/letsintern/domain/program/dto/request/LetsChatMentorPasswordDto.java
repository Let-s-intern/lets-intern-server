package com.letsintern.letsintern.domain.program.dto.request;

import lombok.Builder;

@Builder
public record LetsChatMentorPasswordDto(String mentorPassword) {
    public static LetsChatMentorPasswordDto of(String mentorPassword) {
        return LetsChatMentorPasswordDto.builder()
                .mentorPassword(mentorPassword)
                .build();
    }
}
