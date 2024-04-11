package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProgramQueryRepository {
    Optional<ProgramDetailVo> findProgramDetailVo(Long programId);

    void updateAllProgramStatusClosedByDueDate(LocalDateTime now);

    List<Long> findAllProgramIdListAndUpdateStatusToDone(LocalDateTime now);
}
