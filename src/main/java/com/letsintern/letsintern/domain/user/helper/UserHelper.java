package com.letsintern.letsintern.domain.user.helper;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserUpdateRequestDTO;
import com.letsintern.letsintern.domain.user.exception.DuplicateUser;
import com.letsintern.letsintern.domain.user.exception.MismatchPassword;
import com.letsintern.letsintern.domain.user.exception.RefreshTokenNotFound;
import com.letsintern.letsintern.domain.user.exception.UserNotFound;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.domain.user.util.RedisUtil;
import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import com.letsintern.letsintern.domain.user.vo.UserVo;
import com.letsintern.letsintern.global.config.user.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PrincipalDetailsService principalDetailsService;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;


    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String createRandomPw() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public Authentication userAuthorizationInput(User user) {
        UserDetails userDetails = principalDetailsService.loadUserByUserId(user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public void matchesRefreshToken(String refreshToken, User user) {
        String redisRefreshToken = redisUtil.getRefreshToken(user.getId().toString());
        if(redisRefreshToken == null || !redisRefreshToken.equals(refreshToken)) {
            throw RefreshTokenNotFound.EXCEPTION;
        }
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFound.EXCEPTION);
    }

    public void findDuplicateUser(UserVo userVo) {
        if(userRepository.findByEmailOrPhoneNum(userVo.getEmail(), userVo.getPhoneNum()).isPresent()) {
            throw DuplicateUser.EXCEPTION;
        }
    }

    public User findForSignIn(UserSignInRequestDTO userSignInRequestDTO) {
        User findUser = userRepository.findByEmail(userSignInRequestDTO.getEmail())
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        if(!matchesPassword(userSignInRequestDTO.getPassword(), findUser.getPassword())) {
            throw MismatchPassword.EXCEPTION;
        }

        return findUser;
    }

    /* 사용자 상세 정보 존재 여부 확인 (대학, 전공) */
    public boolean checkDetailInfoExist(User user) {
        if(user.getUniversity() == null || user.getMajor() == null) return false;
        return true;
    }

    public Long updateUserInfo(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        if(userUpdateRequestDTO.getName() != null) {
            user.setName(userUpdateRequestDTO.getName());
        }

        if(userUpdateRequestDTO.getEmail() != null) {
            if(userRepository.findByEmail(userUpdateRequestDTO.getEmail()).isPresent()) {
                throw DuplicateUser.EXCEPTION;
            }
            user.setEmail(userUpdateRequestDTO.getEmail());
        }

        if(userUpdateRequestDTO.getPhoneNum() != null) {
            if(userRepository.findByPhoneNum(userUpdateRequestDTO.getPhoneNum()).isPresent()) {
                throw DuplicateUser.EXCEPTION;
            }
            user.setPhoneNum(userUpdateRequestDTO.getPhoneNum());
        }

        if(userUpdateRequestDTO.getUniversity() != null) {
            user.setUniversity(userUpdateRequestDTO.getUniversity());
        }

        if(userUpdateRequestDTO.getMajor() != null) {
            user.setMajor(user.getMajor());
        }

        return user.getId();
    }

    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        if(!matchesPassword(currentPassword, user.getPassword())) {
            throw MismatchPassword.EXCEPTION;
        }

        user.setPassword(encodePassword(newPassword));
    }

    public List<AdminUserVo> getAdminUserTotalList(Pageable pageable) {
        return userRepository.findAllAdminUserVo(pageable);
    }

    public List<AdminUserVo> getAdminUserList(String type, String keyword) {
        switch (type) {
            case "name":
                return userRepository.findAdminUserVoByName(keyword);
            case "email":
                return userRepository.findAdminUserVoByEmail(keyword);
            case "phoneNum":
                return userRepository.findAdminUserVoByPhoneNum(keyword);
            default:
                return new ArrayList<>();
        }
    }

    public Long setUserManager(Long userId, Long managerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        user.setManagerId(managerId);
        return user.getId();
    }
}
