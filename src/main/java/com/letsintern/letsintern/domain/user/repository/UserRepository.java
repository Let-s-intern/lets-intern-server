package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNameAndPhoneNum(String name, String phoneNum);
}
