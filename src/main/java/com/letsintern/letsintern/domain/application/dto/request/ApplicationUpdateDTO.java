package com.letsintern.letsintern.domain.application.dto.request;

import com.letsintern.letsintern.domain.application.domain.ApplicationAttendance;
import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationUpdateDTO {

    private ApplicationStatus status;

    private Boolean isApproved;

    @Nullable
    private Boolean feeIsConfirmed;

    @Nullable
    private Integer grade;

    @Nullable
    private String wishCompany;

    @Nullable
    private String wishJob;

    @Nullable
    private String applyMotive;

    @Nullable
    private ApplicationAttendance attendance;

}
