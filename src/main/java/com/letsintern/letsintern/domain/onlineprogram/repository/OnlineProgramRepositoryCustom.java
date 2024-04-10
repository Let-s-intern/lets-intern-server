package com.letsintern.letsintern.domain.onlineprogram.repository;

import com.letsintern.letsintern.domain.onlineprogram.vo.OnlineProgramAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OnlineProgramRepositoryCustom {

    Page<OnlineProgramAdminVo> findAllOnlineProgramAdminVos(Pageable pageable);

}
