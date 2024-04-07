package com.letsintern.letsintern.domain.banner.domain.programbanner.repository;

import com.letsintern.letsintern.domain.banner.domain.programbanner.vo.ProgramBannerAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramBannerRepositoryCustom {

    Page<ProgramBannerAdminVo> findAllProgramBannerAdminVos(Pageable pageable);
}
