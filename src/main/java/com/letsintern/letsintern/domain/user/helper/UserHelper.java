package com.letsintern.letsintern.domain.user.helper;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.UserExistRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpDTO;
import com.letsintern.letsintern.domain.user.mapper.UserMapper;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public boolean existingUser(UserExistRequestDTO userExistRequestDTO) {
        return userRepository.existsByNameAndPhoneNum(userExistRequestDTO.getName(), userExistRequestDTO.getPhoneNum());
    }

    public Long signUp(UserSignUpDTO userSignUpDTO) {
        User newUser = userMapper.toEntity(userSignUpDTO);
        User savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }
}
