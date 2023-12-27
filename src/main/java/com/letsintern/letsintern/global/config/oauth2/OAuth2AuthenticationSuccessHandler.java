package com.letsintern.letsintern.global.config.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsintern.letsintern.domain.user.dto.response.TokenResponse;
import com.letsintern.letsintern.domain.user.exception.Oauth2DuplicateUser;
import com.letsintern.letsintern.domain.user.oauth2.CookieAuthorizationRequestRepository;
import com.letsintern.letsintern.global.common.util.CookieUtils;
import com.letsintern.letsintern.global.config.jwt.TokenProvider;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${spring.security.oauth2.authorizedRedirectUri}")
    private String redirectUri;
    private final TokenProvider tokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl;
        if(((PrincipalDetails) authentication.getPrincipal()).isDifferentProvider())
            targetUrl = determineTargetUrlException(request, response, authentication);
        else
            targetUrl = determineTargetUrl(request, response, authentication);

        if(response.isCommitted()) {
            logger.debug("Response has already been committed");
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String getRedirectUri(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("Redirect URIs are not matched");
        }

        return redirectUri.orElse(getDefaultTargetUrl());
    }
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String targetUrl = getRedirectUri(request, response);

        TokenResponse tokenResponse = TokenResponse.of(
                tokenProvider.createOAuth2AccessToken(authentication),
                tokenProvider.createOAuth2RefreshToken(authentication)
        );

        String result = null;

        try {
            result = objectMapper.writeValueAsString(tokenResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return UriComponentsBuilder.fromHttpUrl(targetUrl)
                .queryParam("result", result)
                .build().toUriString();
    }

    protected String determineTargetUrlException(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String targetUrl = getRedirectUri(request, response);
        String error = null;

        try {
            error = objectMapper.writeValueAsString(Oauth2DuplicateUser.EXCEPTION.getErrorReason());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return UriComponentsBuilder.fromHttpUrl(targetUrl)
                .queryParam("error", error)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequest(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create(redirectUri);

        if(authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
            return true;
        }
        return false;
    }
}
