package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProgramRepositoryCustom {

    Integer maxTh(ProgramType type);

    List<ProgramThumbnailVo> findProgramThumbnails(Pageable pageable);

    List<ProgramThumbnailVo> findProgramThumbnailsByType(String type, Pageable pageable);

    Optional<ProgramDetailVo> findProgramDetailVo(Long programId);

    List<Program> findAllAdmin(Pageable pageable);

    List<Program> findAllAdminByTypeAndTh(String type, Integer th, Pageable pageable);

    List<Program> findAllAdminByType(String type, Pageable pageable);

    void updateAllByDueDate(Date now);
}
