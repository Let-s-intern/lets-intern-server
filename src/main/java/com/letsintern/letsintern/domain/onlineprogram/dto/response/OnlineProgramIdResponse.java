package com.letsintern.letsintern.domain.onlineprogram.dto.response;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OnlineProgramIdResponse(@NotNull Long onlineProgramId) {
    public static OnlineProgramIdResponse from(OnlineProgram onlineProgram) {
        return OnlineProgramIdResponse.builder()
                .onlineProgramId(onlineProgram.getId())
                .build();
    }
}
