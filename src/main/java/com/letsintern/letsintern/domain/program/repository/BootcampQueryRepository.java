package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.bootcamp.BootcampDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BootcampQueryRepository {
    Optional<BootcampDetailVo> findBootcampDetailVo(Long bootcampId);
    Page<ProgramThumbnailVo> findProgramThumbnailsByType(Pageable pageable);
}
