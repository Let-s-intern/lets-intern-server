package com.letsintern.letsintern.domain.faq.repository;

import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.ProgramType;

import java.util.List;

public interface FaqRepositoryCustom {

    FaqVo findVoById(Long id);

    List<FaqVo> findVoListByProgramType(ProgramType programType);
}
