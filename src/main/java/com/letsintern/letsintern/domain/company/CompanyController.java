package com.letsintern.letsintern.domain.company;

import com.letsintern.letsintern.domain.company.dto.request.CompanyCreateRequestDTO;
import com.letsintern.letsintern.domain.company.dto.response.CompanyIdResponse;
import com.letsintern.letsintern.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/create")
    public CompanyIdResponse createCompany(@RequestBody CompanyCreateRequestDTO companyCreateRequestDTO) {
        return companyService.createCompany(companyCreateRequestDTO);
    }
}
