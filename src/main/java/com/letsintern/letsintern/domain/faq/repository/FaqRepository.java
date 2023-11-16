package com.letsintern.letsintern.domain.faq.repository;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    List<Faq> findAllByProgramId(Long programId);
}
