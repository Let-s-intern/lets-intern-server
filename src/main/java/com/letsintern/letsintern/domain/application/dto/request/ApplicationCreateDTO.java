package com.letsintern.letsintern.domain.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationCreateDTO {

    private Long programId;

    private Long userId;

    private String applyMotive;

    private String question;

}
