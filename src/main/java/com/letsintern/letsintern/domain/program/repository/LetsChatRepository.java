package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetsChatRepository extends JpaRepository<LetsChat, Long>, LetsChatQueryRepository {
}
