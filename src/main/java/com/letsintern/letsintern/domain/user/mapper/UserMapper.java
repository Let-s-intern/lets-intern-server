package com.letsintern.letsintern.domain.user.mapper;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequest;
import com.letsintern.letsintern.domain.user.dto.response.TokenResponse;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.global.config.jwt.TokenProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User toEntity(UserSignUpRequest userSignUpRequest, String encodedPassword) {
        return User.of(userSignUpRequest, encodedPassword);
    }

    public TokenResponse toTokenResponse(String accessToken, String refreshToken) {
        return TokenResponse.of(accessToken, refreshToken);
    }

    public UserIdResponseDTO toUserIdResponseDTO(Long userId) {
        return UserIdResponseDTO.of(userId);
    }

    public UserTotalListDTO toUserTotalListResponseDTO(List<User> userList) {
        return UserTotalListDTO.from(userList);
    }
}
