package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProgramRepositoryCustom {

    Page<ProgramThumbnailVo> findProgramThumbnails(Pageable pageable);

    Page<ProgramThumbnailVo> findProgramThumbnailsByType(String type, Pageable pageable);

    Optional<ProgramDetailVo> findProgramDetailVo(Long programId);

    Page<Program> findAllAdmin(Pageable pageable);

    Page<Program> findAllAdminByTypeAndTh(String type, Integer th, Pageable pageable);

    Page<Program> findAllAdminByType(String type, Pageable pageable);

    void updateAllByDueDate(Date now);

    void updateAllByEndDate(Date now);
}
