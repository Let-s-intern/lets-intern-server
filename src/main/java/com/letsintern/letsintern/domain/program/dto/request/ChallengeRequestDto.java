package com.letsintern.letsintern.domain.program.dto.request;

public record ChallengeRequestDto(
        Integer challengeTopic,
        Integer challengeType,
        String openKakaoLink,
        String openKakaoPassword
) {
}
