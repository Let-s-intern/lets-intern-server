package com.letsintern.letsintern.domain.program.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProgramMentorPasswordResponse {

    private String mentorPassword;

    @Builder
    private ProgramMentorPasswordResponse(String mentorPassword) {
        this.mentorPassword = mentorPassword;
    }

    public static ProgramMentorPasswordResponse from(String mentorPassword) {
        return ProgramMentorPasswordResponse.builder()
                .mentorPassword(mentorPassword)
                .build();
    }
}
