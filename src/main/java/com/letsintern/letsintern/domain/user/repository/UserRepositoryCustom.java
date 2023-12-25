package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.user.vo.AdminMangerVo;
import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    Page<AdminUserVo> findAllAdminUserVo(Pageable pageable);

    List<AdminMangerVo> findManagerList();
}
