package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;

import java.util.Optional;

public interface ChallengeQueryRepository {
    Optional<ChallengeDetailVo> findChallengeDetailVo(Long challengeId);
}
