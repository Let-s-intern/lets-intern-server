package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ChallengeTopic;

public record ChallengeRequestDto(
        Integer challengeTopic,
        Integer challengeType,
        String openKakaoLink,
        String openKakaoPassword
) {
}
