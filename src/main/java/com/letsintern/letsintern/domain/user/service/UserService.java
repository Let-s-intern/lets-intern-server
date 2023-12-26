package com.letsintern.letsintern.domain.user.service;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.domain.user.dto.request.*;
import com.letsintern.letsintern.domain.user.dto.response.*;
import com.letsintern.letsintern.domain.user.exception.UserNotFound;
import com.letsintern.letsintern.domain.user.helper.UserHelper;
import com.letsintern.letsintern.domain.user.mapper.UserMapper;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.domain.user.util.EmailUtil;
import com.letsintern.letsintern.global.config.jwt.TokenProvider;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final EmailUtil emailUtil;

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
    public void addUserDetailInfo(User user, String university, String major) {
        user.setUniversity(university);
        user.setMajor(major);
        if(user.getRole().equals(UserRole.ROLE_ANONYMOUS)) user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    @Transactional
    public void signOut(PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        tokenProvider.deleteRefreshToken(user.getId());
    }

    @Transactional
    public void changePassword(PrincipalDetails principalDetails, PwChangeDTO pwChangeDTO) {
        userHelper.changePassword(
                principalDetails.getUser().getId(),
                pwChangeDTO.getCurrentPassword(),
                pwChangeDTO.getNewPassword()
        );
    }

    @Transactional
    public void sendPwResetMail(PwResetMailDTO pwResetMailDTO) {
        User user = userRepository.findByEmailAndName(pwResetMailDTO.getEmail(), pwResetMailDTO.getName())
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });
        String randomPw = userHelper.createRandomPw();
        user.setPassword(userHelper.encodePassword(randomPw));
        emailUtil.sendEmail(pwResetMailDTO.getEmail(), randomPw);
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

    public Boolean checkDetailInfoExist(PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return userHelper.checkDetailInfoExist(user);
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
    public UserIdResponseDTO updateAdminUser(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) {
        return userMapper.toUserIdResponseDTO(userHelper.updateAdminUserInfo(userId, userUpdateRequestDTO));
    }

    public AdminUserListResponseDTO getAdminUserTotalList(Pageable pageable) {
        return userMapper.toUserTotalListResponseDTO(userHelper.getAdminUserTotalList(pageable));
    }

    public AdminManagerListResponse getAdminManagerList() {
        return AdminManagerListResponse.from(userRepository.findManagerList());
    }

    @Transactional
    public UserIdResponseDTO setUserManager(Long userId, Long managerId) {
        return userMapper.toUserIdResponseDTO(userHelper.setUserManager(userId, managerId));
    }

    public Boolean checkIsAdmin(PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return user.getRole().equals(UserRole.ROLE_ADMIN);
    }

    public User getAdminUser(Long userId) {
        return userHelper.findUser(userId);
    }

    @Transactional
    public void deleteAdminUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });
        tokenProvider.deleteRefreshToken(user.getId());
        userRepository.delete(user);
    }
}
