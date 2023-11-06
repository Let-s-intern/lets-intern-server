package com.letsintern.letsintern.domain.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationCreateDTO {

    private Integer grade;

    private String wishCompany;

    private String wishJob;

    private String applyMotive;

}
