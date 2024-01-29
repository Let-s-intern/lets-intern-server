package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.user.vo.AdminMangerVo;
import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    Page<AdminUserVo> findAllAdminUserVo(Pageable pageable);

    Page<AdminUserVo> findAllAdminUserVoFiltered(ProgramType programType, Integer programTh, String name, String email, String phoneNum, Pageable pageable);

    List<AdminMangerVo> findManagerList();
}
