package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatDetailVo;

import java.util.Optional;

public interface LetsChatQueryRepository {

    Optional<LetsChatDetailVo> findLetsChatDetailVo(Long letsChatId);
}
