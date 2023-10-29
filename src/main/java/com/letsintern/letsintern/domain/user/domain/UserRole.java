package com.letsintern.letsintern.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_ADMIN("운영진"),
    ROLE_USER("사용자"),
    ROLE_ANONYMOUS("임시");

    @JsonValue private final String userRole;

    @JsonCreator
    public static UserRole parsing(String input) {
        return Stream.of(UserRole.values())
                .filter(category -> category.getUserRole().equals(input))
                .findFirst()
                .orElse(null);
    }
}
