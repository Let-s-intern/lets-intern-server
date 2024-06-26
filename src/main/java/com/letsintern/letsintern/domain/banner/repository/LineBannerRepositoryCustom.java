package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.LineBannerAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LineBannerRepositoryCustom {

    Page<LineBannerAdminVo> findAllLineBannerAdminVos(Pageable pageable);
}
