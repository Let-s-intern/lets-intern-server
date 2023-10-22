package com.letsintern.letsintern.domain.company.helper;

import com.letsintern.letsintern.domain.company.domain.Company;
import com.letsintern.letsintern.domain.company.dto.request.CompanyCreateRequestDTO;
import com.letsintern.letsintern.domain.company.mapper.CompanyMapper;
import com.letsintern.letsintern.domain.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyHelper {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public Long createCompany(CompanyCreateRequestDTO companyCreateRequestDTO) {
        Company newCompany = companyMapper.toEntity(companyCreateRequestDTO);
        Company savedCompany = companyRepository.save(newCompany);

        return savedCompany.getId();
    }
}
