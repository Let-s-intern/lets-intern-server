package com.letsintern.letsintern.domain.file.repository;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.file.domain.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findById(Long fileId);

    Page<File> findAllBy(Pageable pageable);
    Page<File> findAllByContentsTopic(ContentsTopic contentsTopic, Pageable pageable);

}
