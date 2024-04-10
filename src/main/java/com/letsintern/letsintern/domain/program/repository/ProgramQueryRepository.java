package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;

import java.util.Optional;

public interface ProgramQueryRepository {
    Optional<ProgramDetailVo> findProgramDetailVo(Long programId);
}
