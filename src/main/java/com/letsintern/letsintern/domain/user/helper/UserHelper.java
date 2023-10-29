package com.letsintern.letsintern.domain.user.helper;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequest;
import com.letsintern.letsintern.domain.user.exception.DuplicateUser;
import com.letsintern.letsintern.domain.user.exception.MismatchPassword;
import com.letsintern.letsintern.domain.user.exception.UserNotFound;
import com.letsintern.letsintern.domain.user.mapper.UserMapper;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.domain.user.vo.UserVo;
import com.letsintern.letsintern.global.config.user.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PrincipalDetailsService principalDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Authentication userAuthorizationInput(User user) {
        UserDetails userDetails = principalDetailsService.loadUserByUserId(user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public void findDuplicateUser(UserVo userVo) {
        if(userRepository.findByEmail(userVo.getEmail()).isPresent()) {
            throw DuplicateUser.EXCEPTION;
        }
    }

    public User findForSignIn(UserSignInRequest userSignInRequest) {
        User findUser = userRepository.findByEmail(userSignInRequest.getEmail())
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });

        if(!matchesPassword(userSignInRequest.getPassword(), findUser.getPassword())) {
            throw MismatchPassword.EXCEPTION;
        }

        return findUser;
    }

    public List<User> getUserTotalList() {
        return userRepository.findAll();
    }
}
