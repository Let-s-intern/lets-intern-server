package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ChallengeTopic;

public record ChallengeRequestDto(
        ChallengeTopic challengeTopic,
        String openKakaoLink,
        String openKakaoPassword
) {
}
