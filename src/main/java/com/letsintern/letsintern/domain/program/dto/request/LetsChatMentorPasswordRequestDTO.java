package com.letsintern.letsintern.domain.program.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LetsChatMentorPasswordRequestDTO {

    @NotNull
    private String mentorPassword;

}
