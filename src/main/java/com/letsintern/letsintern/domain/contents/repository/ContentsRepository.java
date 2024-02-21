package com.letsintern.letsintern.domain.contents.repository;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentsRepository extends JpaRepository<Contents, Long>, ContentsRepositoryCustom {

}
