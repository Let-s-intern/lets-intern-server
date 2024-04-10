package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.bootcamp.BootcampDetailVo;

import java.util.Optional;

public interface BootcampQueryRepository {
    Optional<BootcampDetailVo> findBootcampDetailVo(Long bootcampId);
}
