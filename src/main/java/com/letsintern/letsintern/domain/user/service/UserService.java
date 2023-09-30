package com.letsintern.letsintern.domain.user.service;

import com.letsintern.letsintern.domain.user.dto.request.UserExistRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.domain.user.helper.UserHelper;
import com.letsintern.letsintern.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserHelper userHelper;
    private final UserMapper userMapper;

    @Transactional
    public boolean existingUser(UserExistRequestDTO userExistRequestDTO) {
        return userHelper.existingUser(userExistRequestDTO);
    }

    @Transactional
    public UserIdResponseDTO signUp(UserSignUpDTO userSignUpDTO) {
        return userMapper.toUserIdResponseDTO(userHelper.signUp(userSignUpDTO));
    }

    @Transactional
    public UserTotalListDTO getUserTotalList() {
        return userMapper.toUserTotalListResponseDTO(userHelper.getUserTotalList());
    }
}
