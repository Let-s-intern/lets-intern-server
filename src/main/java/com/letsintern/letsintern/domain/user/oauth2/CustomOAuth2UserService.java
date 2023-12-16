package com.letsintern.letsintern.domain.user.oauth2;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.exception.DuplicateUser;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());

        if(!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email Not Found From OAuth2 Provider");
        }


        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);
        if(user != null) {
            if(!user.getAuthProvider().equals(authProvider)) {
                throw DuplicateUser.EXCEPTION;
            }
            user = updateUser(user, oAuth2UserInfo);            // 이미 회원 가입된 이메일
        } else {
            user = registerUser(oAuth2UserInfo, authProvider);  // 신규 가입
        }

        return new PrincipalDetails(user);
    }

    private User registerUser(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User user = User.ofOAuth(oAuth2UserInfo, authProvider);
        return userRepository.save(user);
    }

    private User updateUser(User user, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.save(user.updateFrom(oAuth2UserInfo));
    }
}