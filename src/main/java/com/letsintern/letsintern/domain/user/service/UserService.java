package com.letsintern.letsintern.domain.user.service;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.TokenRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserUpdateRequestDTO;
import com.letsintern.letsintern.domain.user.dto.response.TokenResponse;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserInfoResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.domain.user.helper.UserHelper;
import com.letsintern.letsintern.domain.user.mapper.UserMapper;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.global.config.jwt.TokenProvider;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
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
    public UserIdResponseDTO signUp(UserSignUpRequestDTO userSignUpRequestDTO) {
        // 기존 가입 여부 확인
        userHelper.findDuplicateUser(userSignUpRequestDTO.getUserVo());

        final String encodedPassword = userHelper.encodePassword(userSignUpRequestDTO.getUserVo().getPassword());
        final User user = userMapper.toEntity(userSignUpRequestDTO, encodedPassword);
        return userMapper.toUserIdResponseDTO(userRepository.save(user).getId());
    }

    @Transactional
    public TokenResponse signIn(UserSignInRequestDTO userSignInRequestDTO) {
        final User user = userHelper.findForSignIn(userSignInRequestDTO);
        final Authentication authentication = userHelper.userAuthorizationInput(user);

        final String accessToken = tokenProvider.createAccessToken(user.getId(), authentication);
        final String refreshToken = tokenProvider.createRefreshToken(user.getId(), authentication);

        return userMapper.toTokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public void addUserDetailInfo(PrincipalDetails principalDetails, String university, String major) {
        final User user = principalDetails.getUser();
        user.setUniversity(university);
        user.setMajor(major);
        userRepository.save(user);
    }

    @Transactional
    public void signOut(PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        tokenProvider.deleteRefreshToken(user.getId());
    }

    @Transactional
    public void withdraw(PrincipalDetails principalDetails) {
        signOut(principalDetails);
        User user = principalDetails.getUser();
        userRepository.delete(user);
    }

    @Transactional
    public TokenResponse reissueToken(TokenRequestDTO tokenRequestDTO) {
        final String refreshToken = tokenRequestDTO.getRefreshToken();
        final User user = userHelper.findUser(Long.parseLong(tokenProvider.getTokenUserId(refreshToken)));
        final Authentication authentication = userHelper.userAuthorizationInput(user);

        tokenProvider.validateRefreshToken(refreshToken);
        userHelper.matchesRefreshToken(refreshToken, user);

        final String newAccessToken = tokenProvider.createAccessToken(user.getId(), authentication);
        return userMapper.toTokenResponse(newAccessToken, refreshToken);
    }

    public UserInfoResponseDTO getUserInfo(PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return userMapper.toUserInfoResponseDTO(user);
    }

    @Transactional
    public UserIdResponseDTO updateUserInfo(UserUpdateRequestDTO userUpdateRequestDTO, PrincipalDetails principalDetails) {
        return userMapper.toUserIdResponseDTO(
                userHelper.updateUserInfo(principalDetails.getUser().getId(), userUpdateRequestDTO)
        );
    }

    @Transactional
    public UserTotalListDTO getUserTotalList() {
        return userMapper.toUserTotalListResponseDTO(userHelper.getUserTotalList());
    }


}
