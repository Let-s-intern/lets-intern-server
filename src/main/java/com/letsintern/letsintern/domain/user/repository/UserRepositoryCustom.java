package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    List<AdminUserVo> findAllAdminUserVo(Pageable pageable);

    List<AdminUserVo> findAdminUserVoByName(String name);

    List<AdminUserVo> findAdminUserVoByEmail(String email);

    List<AdminUserVo> findAdminUserVoByPhoneNum(String phoneNum);
}
