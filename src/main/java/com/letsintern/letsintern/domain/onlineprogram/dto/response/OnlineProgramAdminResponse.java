package com.letsintern.letsintern.domain.onlineprogram.dto.response;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OnlineProgramAdminResponse(
        OnlineProgram onlineProgramInfo
) {
    public static OnlineProgramAdminResponse from(OnlineProgram onlineProgram) {
        return OnlineProgramAdminResponse.builder()
                .onlineProgramInfo(onlineProgram)
                .build();
    }
}
