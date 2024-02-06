package com.letsintern.letsintern.domain.notice.repository;

import com.letsintern.letsintern.domain.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {

    Page<Notice> findAllByProgramIdOrderByIdDesc(Long programId, Pageable pageable);

}
