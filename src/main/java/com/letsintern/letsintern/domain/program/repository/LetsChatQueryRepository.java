package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;

import java.util.Optional;

public interface LetsChatQueryRepository {

    Optional<ProgramDetailVo> findLetsChatDetailVo(Long letsChatId);
}
