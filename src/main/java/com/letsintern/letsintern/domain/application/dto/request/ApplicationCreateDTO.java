package com.letsintern.letsintern.domain.application.dto.request;

import com.letsintern.letsintern.domain.application.domain.InflowPath;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationCreateDTO {

    private Integer grade;

    private String wishCompany;

    private String wishJob;

    private String applyMotive;

    private InflowPath inflowPath;

    private String guestName;

    private String guestPhoneNum;

    private String guestEmail;

}
