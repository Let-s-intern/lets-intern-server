package com.letsintern.letsintern.domain.file.repository;

import com.letsintern.letsintern.domain.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findById(Long fileId);

}
