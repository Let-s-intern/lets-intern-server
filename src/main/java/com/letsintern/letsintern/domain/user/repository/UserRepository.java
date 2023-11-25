package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    boolean existsByNameAndPhoneNum(String name, String phoneNum);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndName(String email, String name);

    Optional<User> findByEmailOrPhoneNum(String email, String phoneNum);

    Optional<User> findByPhoneNum(String email);
}
