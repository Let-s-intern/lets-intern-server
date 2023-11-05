package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramRepositoryCustom {

    Integer maxTh(ProgramType type);

    List<ProgramThumbnailVo> findProgramThumbnailByStatus(ProgramStatus status, Pageable pageable);

    List<ProgramThumbnailVo> findProgramThumbnailByTypeAndStatus(ProgramType type, ProgramStatus status, Pageable pageable);
}
