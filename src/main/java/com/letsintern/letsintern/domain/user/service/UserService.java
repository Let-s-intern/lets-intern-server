package com.letsintern.letsintern.domain.user.service;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequest;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequest;
import com.letsintern.letsintern.domain.user.dto.response.TokenResponse;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.domain.user.helper.UserHelper;
import com.letsintern.letsintern.domain.user.mapper.UserMapper;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserHelper userHelper;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    @Transactional
    public UserIdResponseDTO signUp(UserSignUpRequest userSignUpRequest) {
        // 기존 가입 여부 확인
        userHelper.findDuplicateUser(userSignUpRequest.getUserVo());

        final String encodedPassword = userHelper.encodePassword(userSignUpRequest.getUserVo().getPassword());
        final User user = userMapper.toEntity(userSignUpRequest, encodedPassword);
        return userMapper.toUserIdResponseDTO(userRepository.save(user).getId());
    }

    @Transactional
    public TokenResponse signIn(UserSignInRequest userSignInRequest) {
        final User user = userHelper.findForSignIn(userSignInRequest);
        final Authentication authentication = userHelper.userAuthorizationInput(user);

        final String accessToken = tokenProvider.createAccessToken(user.getId(), authentication);
        final String refreshToken = tokenProvider.createRefreshToken(user.getId(), authentication);

        return userMapper.toTokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public UserTotalListDTO getUserTotalList() {
        return userMapper.toUserTotalListResponseDTO(userHelper.getUserTotalList());
    }
}
