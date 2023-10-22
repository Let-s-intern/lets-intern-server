package com.letsintern.letsintern.domain.company.repository;

import com.letsintern.letsintern.domain.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
