package com.letsintern.letsintern.domain.banner.domain.popup.repository;

import com.letsintern.letsintern.domain.banner.domain.popup.vo.PopupAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PopupRepositoryCustom {

    Page<PopupAdminVo> findAllPopupAdminVos(Pageable pageable);
}
