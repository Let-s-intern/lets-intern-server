package com.letsintern.letsintern.domain.faq.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Getter;

@Getter
public class FaqUpdateDTO {

    private ProgramType programType;
    private String question;
    private String answer;

}
