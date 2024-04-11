package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramRequestType;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProgramQueryRepository {
    Optional<ProgramDetailVo> findProgramDetailVo(Long programId);
    void updateAllProgramStatusClosedByDueDate(LocalDateTime now);
    List<Long> findAllProgramIdListAndUpdateStatusToDone(LocalDateTime now);
    Page<Program> findAllProgramByTypeAndTh(ProgramRequestType type, Integer th, Pageable pageable);
}
