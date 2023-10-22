package com.letsintern.letsintern.domain.company.service;

import com.letsintern.letsintern.domain.company.dto.request.CompanyCreateRequestDTO;
import com.letsintern.letsintern.domain.company.dto.response.CompanyIdResponse;
import com.letsintern.letsintern.domain.company.helper.CompanyHelper;
import com.letsintern.letsintern.domain.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyHelper companyHelper;
    private final CompanyMapper companyMapper;

    public CompanyIdResponse createCompany(CompanyCreateRequestDTO companyCreateRequestDTO) {
        return companyMapper.toCompanyIdResponse(companyHelper.createCompany(companyCreateRequestDTO));
    }
}
