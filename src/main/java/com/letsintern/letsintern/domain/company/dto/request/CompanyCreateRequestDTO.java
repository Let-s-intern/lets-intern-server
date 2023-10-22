package com.letsintern.letsintern.domain.company.dto.request;

import com.letsintern.letsintern.domain.company.domain.CompanyStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyCreateRequestDTO {

    private Long userId;

    private String name;

    private String job;

    private String applicationUrl;

    private String interviewUrl;

    private CompanyStatus status;
}
