package com.letsintern.letsintern.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdResponseDTO {

    private Long userId;

    @Builder
    private UserIdResponseDTO(Long userId) {
        this.userId = userId;
    }

    public static UserIdResponseDTO of(Long userId) {
        return UserIdResponseDTO.builder()
                .userId(userId)
                .build();
    }
}
