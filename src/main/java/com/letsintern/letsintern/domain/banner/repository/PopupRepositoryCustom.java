package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PopupRepositoryCustom {

    Page<PopupAdminVo> findAllPopupAdminVos(Pageable pageable);
}
