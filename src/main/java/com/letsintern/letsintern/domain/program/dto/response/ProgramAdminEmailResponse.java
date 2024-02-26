package com.letsintern.letsintern.domain.program.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramAdminEmailResponse {

    private List<String> emailAddressList;

    private String emailContents;

    @Builder
    private ProgramAdminEmailResponse(List<String> emailAddressList, String emailContents) {
        this.emailAddressList = emailAddressList;
        this.emailContents = emailContents;
    }

    public static ProgramAdminEmailResponse of(List<String> emailAddressList, String emailContents) {
        return ProgramAdminEmailResponse.builder()
                .emailAddressList(emailAddressList)
                .emailContents(emailContents)
                .build();
    }
}
