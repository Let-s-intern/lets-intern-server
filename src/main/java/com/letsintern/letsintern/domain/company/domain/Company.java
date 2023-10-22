package com.letsintern.letsintern.domain.company.domain;

import com.letsintern.letsintern.domain.company.dto.request.CompanyCreateRequestDTO;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String name;

    @NotNull
    String job;

    @NotNull
    String applicationUrl;

    @Nullable
    String interviewUrl;

    @Nullable
    CompanyStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Company(String name, String job, String applicationUrl, String interviewUrl,
                    CompanyStatus status, User user) {
        this.name = name;
        this.job = job;
        this.applicationUrl = applicationUrl;
        this.user = user;

        if(interviewUrl != null) this.interviewUrl = interviewUrl;
        if(status != null) this.status = status;
    }

    public static Company of(CompanyCreateRequestDTO companyCreateRequestDTO, User user) {
        return Company.builder()
                .name(companyCreateRequestDTO.getName())
                .job(companyCreateRequestDTO.getJob())
                .applicationUrl(companyCreateRequestDTO.getApplicationUrl())
                .interviewUrl(companyCreateRequestDTO.getInterviewUrl())
                .status(companyCreateRequestDTO.getStatus())
                .user(user)
                .build();
    }
}
