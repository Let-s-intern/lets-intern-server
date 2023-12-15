package com.letsintern.letsintern.domain.user.oauth2;

import com.letsintern.letsintern.domain.user.oauth2.user.GoogleOAuth2User;
import com.letsintern.letsintern.domain.user.oauth2.user.KakaoOAuth2User;
import com.letsintern.letsintern.domain.user.oauth2.user.NaverOAuth2User;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        switch (authProvider) {
            case GOOGLE -> {
                return new GoogleOAuth2User(attributes);
            }
            case KAKAO -> {
                return new KakaoOAuth2User(attributes);
            }
            case NAVER -> {
                return new NaverOAuth2User(attributes);
            }

            default -> throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
