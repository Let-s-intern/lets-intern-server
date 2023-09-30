package com.letsintern.letsintern.domain.user.mapper;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserSignUpDTO userSignUpDTO) {
        return User.of(userSignUpDTO);
    }

    public UserIdResponseDTO toUserIdResponseDTO(Long userId) {
        return UserIdResponseDTO.of(userId);
    }
}
