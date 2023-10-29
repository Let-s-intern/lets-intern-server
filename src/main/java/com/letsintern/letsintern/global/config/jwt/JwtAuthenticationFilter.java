package com.letsintern.letsintern.global.config.jwt;

import com.letsintern.letsintern.global.error.exception.TokenValidateException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = tokenProvider.resolveAccessToken(request);

        try {
            if (jwt == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (tokenProvider.validateAccessToken(jwt)) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw TokenValidateException.EXCEPTION;
            }

        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
