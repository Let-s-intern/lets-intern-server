package com.letsintern.letsintern.domain.banner.domain.mainbanner.repository;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.vo.MainBannerAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MainBannerRepositoryCustom {

    Page<MainBannerAdminVo> findAllMainBannerAdminVos(Pageable pageable);
}
