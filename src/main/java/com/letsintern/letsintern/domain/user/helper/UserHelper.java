package com.letsintern.letsintern.domain.user.helper;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserUpdateRequestDTO;
import com.letsintern.letsintern.domain.user.exception.*;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.domain.user.util.RedisUtil;
import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import com.letsintern.letsintern.domain.user.vo.UserVo;
import com.letsintern.letsintern.global.config.user.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PrincipalDetailsService principalDetailsService;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;

    @Transactional
    public void addUserDetailInfo(User user, String university, String major) {
        user.updateUniversity(university);
        user.updateMajor(major);
        if (user.getRole().equals(UserRole.ROLE_ANONYMOUS))
            user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    @Transactional
    public void addUserDetailAccountInfo(User user, AccountType accountType, String accountNumber) {
        user.updateAccountType(accountType);
        user.updateAccountNumber(accountNumber);
        userRepository.save(user);
    }

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
        if (redisRefreshToken == null || !redisRefreshToken.equals(refreshToken)) {
            throw RefreshTokenNotFound.EXCEPTION;
        }
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFound.EXCEPTION);
    }

    public void findDuplicateUser(UserVo userVo) {
        if (userRepository.findByEmail(userVo.getEmail()).isPresent()) {
            throw DuplicateUser.EXCEPTION;
        }
    }

    public User findForSignIn(UserSignInRequestDTO userSignInRequestDTO) {
        User findUser = userRepository.findByEmail(userSignInRequestDTO.getEmail())
                .orElseThrow(() -> UserNotFound.EXCEPTION);

        if (!matchesPassword(userSignInRequestDTO.getPassword(), findUser.getPassword())) {
            throw MismatchPassword.EXCEPTION;
        }

        return findUser;
    }

    /* 사용자 상세 정보 존재 여부 확인 (대학, 전공) */
    public boolean checkDetailInfoExist(User user) {
        return user.getUniversity() != null && user.getMajor() != null;
    }

    /* 사용자 상세 정보 존재 여부 확인 (계좌 유형, 계좌 번호) */
    public boolean checkDetailAccountInfoExist(User user) {
        return user.getAccountType() != null && user.getAccountNumber() != null;
    }

    public Long updateUserInfo(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFound.EXCEPTION);

        if (userUpdateRequestDTO.getName() != null) {
            user.setName(userUpdateRequestDTO.getName());
        }

        if (userUpdateRequestDTO.getEmail() != null) {
            if (userRepository.findByEmail(userUpdateRequestDTO.getEmail()).isPresent()) {
                throw DuplicateUser.EXCEPTION;
            } else user.setEmail(userUpdateRequestDTO.getEmail());
        }

        if (userUpdateRequestDTO.getPhoneNum() != null) {
            user.setPhoneNum(userUpdateRequestDTO.getPhoneNum());
        }

        if (userUpdateRequestDTO.getUniversity() != null) {
            user.setUniversity(userUpdateRequestDTO.getUniversity());
        }

        if (userUpdateRequestDTO.getMajor() != null) {
            user.setMajor(userUpdateRequestDTO.getMajor());
        }

        if (userUpdateRequestDTO.getAccountType() != null) {
            user.setAccountType(userUpdateRequestDTO.getAccountType());
        }

        if (userUpdateRequestDTO.getAccountNumber() != null) {
            user.setAccountNumber(userUpdateRequestDTO.getAccountNumber());
        }

        return user.getId();
    }

    public Long updateAdminUserInfo(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        if (userUpdateRequestDTO.getName() != null) {
            user.setName(userUpdateRequestDTO.getName());
        }

        if (userUpdateRequestDTO.getEmail() != null) {
            if (userRepository.findByEmail(userUpdateRequestDTO.getEmail()).isPresent()) {
                throw DuplicateUser.EXCEPTION;
            } else user.setEmail(userUpdateRequestDTO.getEmail());
        }

        if (userUpdateRequestDTO.getPhoneNum() != null) {
            user.setPhoneNum(userUpdateRequestDTO.getPhoneNum());
        }

        if (userUpdateRequestDTO.getUniversity() != null) {
            user.setUniversity(userUpdateRequestDTO.getUniversity());
        }

        if (userUpdateRequestDTO.getMajor() != null) {
            user.setMajor(user.getMajor());
        }

        if (userUpdateRequestDTO.getManagerId() != null) {
            user.setManagerId(userUpdateRequestDTO.getManagerId());
        }

        if (userUpdateRequestDTO.getRole() != null) {
            user.setRole(userUpdateRequestDTO.getRole());
        }

        if (userUpdateRequestDTO.getAccountType() != null) {
            user.setAccountType(userUpdateRequestDTO.getAccountType());
        }

        if (userUpdateRequestDTO.getAccountNumber() != null) {
            user.setAccountNumber(userUpdateRequestDTO.getAccountNumber());
        }

        return user.getId();
    }

    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        if (!matchesPassword(currentPassword, user.getPassword())) {
            throw MyPageMismatchPassword.EXCEPTION;
        }

        user.setPassword(encodePassword(newPassword));
    }

    public Page<AdminUserVo> getAdminUserTotalList(ProgramType programType, Integer programTh, String name, String email, String phoneNum, Pageable pageable) {
        if (programType != null || programTh != null || name != null || email != null || phoneNum != null) {
            if (programType == null && programTh != null) {
                throw AdminUserFilterOnlyProgramTh.EXCEPTION;
            }
            return userRepository.findAllAdminUserVoFiltered(programType, programTh, name, email, phoneNum, pageable);
        }
        return userRepository.findAllAdminUserVo(pageable);
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
