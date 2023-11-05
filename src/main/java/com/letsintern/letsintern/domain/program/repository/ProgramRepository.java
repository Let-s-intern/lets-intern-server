package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long>, ProgramRepositoryCustom {

    Optional<Program> findById(Long programId);
}
