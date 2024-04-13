package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChallengeQueryRepository {
    Optional<ChallengeDetailVo> findChallengeDetailVo(Long challengeId);
    Page<ProgramThumbnailVo> findProgramThumbnailsByType(Pageable pageable);
}
