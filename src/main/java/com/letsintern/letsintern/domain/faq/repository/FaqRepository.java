package com.letsintern.letsintern.domain.faq.repository;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long>, FaqRepositoryCustom {

}
