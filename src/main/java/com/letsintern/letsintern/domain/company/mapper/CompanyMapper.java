package com.letsintern.letsintern.domain.company.mapper;

import com.letsintern.letsintern.domain.company.domain.Company;
import com.letsintern.letsintern.domain.company.dto.request.CompanyCreateRequestDTO;
import com.letsintern.letsintern.domain.company.dto.response.CompanyIdResponse;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.exception.UserNotFound;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    private final UserRepository userRepository;

    public Company toEntity(CompanyCreateRequestDTO companyCreateRequestDTO) {
        User user = userRepository.findById(companyCreateRequestDTO.getUserId())
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        return Company.of(companyCreateRequestDTO, user);
    }

    public CompanyIdResponse toCompanyIdResponse(Long companyId) {
        return CompanyIdResponse.from(companyId);
    }
}
