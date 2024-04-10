package com.letsintern.letsintern.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record LetsChatMentorAfterSessionResponseDto(
        String title,
        List<String> reviewList
) {
    public static LetsChatMentorAfterSessionResponseDto of(String title, List<String> reviewList) {
        return LetsChatMentorAfterSessionResponseDto.builder()
                .title(title)
                .reviewList(reviewList)
                .build();
    }
}
