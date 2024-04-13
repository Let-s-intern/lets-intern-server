package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.exception.ChallengeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.program.dto.request.ChallengeRequestDto;
import com.letsintern.letsintern.domain.program.exception.ChallengeNotFoundException;
import com.letsintern.letsintern.domain.program.repository.ChallengeRepository;
import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Challenge findChallengeOrThrow(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    public ChallengeDetailVo findChallengeDetailOrThrow(Long challengeId) {
        return challengeRepository.findChallengeDetailVo(challengeId)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    public Page<ProgramThumbnailVo> findProgramList(Pageable pageable) {
        return challengeRepository.findProgramThumbnailsByType(pageable);
    }

    public Challenge saveChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long challengeId) {
        challengeRepository.deleteById(challengeId);
    }
}
