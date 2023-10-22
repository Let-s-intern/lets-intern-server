package com.letsintern.letsintern.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyIdResponse {

    private Long companyId;

    @Builder
    private CompanyIdResponse(Long companyId) {
        this.companyId = companyId;
    }

    public static CompanyIdResponse from(Long companyId) {
        return CompanyIdResponse.builder()
                .companyId(companyId)
                .build();
    }
}
