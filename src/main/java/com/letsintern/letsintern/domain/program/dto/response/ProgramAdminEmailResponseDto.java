package com.letsintern.letsintern.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramAdminEmailResponseDto(
        List<String> emailAddressList,
        String emailContents
) {
    public static ProgramAdminEmailResponseDto of(List<String> emailAddressList, String emailContents) {
        return ProgramAdminEmailResponseDto.builder()
                .emailAddressList(emailAddressList)
                .emailContents(emailContents)
                .build();
    }
}
