package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.ProgramBannerAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramBannerRepositoryCustom {

    Page<ProgramBannerAdminVo> findAllProgramBannerAdminVos(Pageable pageable);
}
