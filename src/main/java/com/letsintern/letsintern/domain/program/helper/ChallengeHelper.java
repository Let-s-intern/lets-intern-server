package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.dto.request.ChallengeRequestDto;
import com.letsintern.letsintern.domain.program.exception.ChallengeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
@RequiredArgsConstructor
@Component
public class ChallengeHelper {
    private final ChallengeRepository challengeRepository;
    public void validateChallengeTypeProgramInput(ChallengeRequestDto challengeRequestDto) {
        if (Objects.isNull(challengeRequestDto.challengeTopic())
                || Objects.isNull(challengeRequestDto.openKakaoLink())
                || Objects.isNull(challengeRequestDto.openKakaoPassword())) {
            throw ChallengeProgramCreateBadRequest.EXCEPTION;
        }
    }

    public Challenge saveChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }
}
