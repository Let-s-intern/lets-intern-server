package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository<T extends Program> extends JpaRepository<T, Long> {
}
