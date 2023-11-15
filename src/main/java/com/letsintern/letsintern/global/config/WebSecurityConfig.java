package com.letsintern.letsintern.global.config;

import com.letsintern.letsintern.global.common.filter.AccessDeniedFilter;
import com.letsintern.letsintern.global.config.jwt.JwtAccessDeniedHandler;
import com.letsintern.letsintern.global.config.jwt.JwtAuthenticationEntryPoint;
import com.letsintern.letsintern.global.config.jwt.JwtAuthenticationFilter;
import com.letsintern.letsintern.global.config.jwt.JwtExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration()
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Value("${server.url}")
    private String SERVER_URL;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionHandlerFilter jwtExceptionHandlerFilter;
    private final AccessDeniedFilter accessDeniedFilter;

    private final String[] SwaggerPatterns = {
            "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"
    };

    private final String[] AdminPatterns = {
            "/user/list/**", "/program/admin/**", "/application/admin/**", "/review/admin/**"
    };

    private final String[] UserAndAnonymousPatterns = {
            "/user/logout", "/application/create/**", "/application/list/mypage"
    };

    private final String[] GetPermittedPatterns = {
            "/program/list/**", "/program/tg"
    };

    private final String[] PostPermittedPatterns = {
        "/user/signup", "/user/token/reissue", "/application/guest/create/**"
    };

    private final String[] PatchPermittedPatterns = {

    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(configurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> {
                    headers.defaultsDisabled().frameOptions(Customizer.withDefaults());
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                            .requestMatchers(HttpMethod.GET, GetPermittedPatterns).permitAll()
                            .requestMatchers(HttpMethod.POST, PostPermittedPatterns).permitAll()
                            .requestMatchers(HttpMethod.PATCH, PatchPermittedPatterns).permitAll()
                            .requestMatchers(SwaggerPatterns).permitAll()
                            .requestMatchers(AdminPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(UserAndAnonymousPatterns).hasAnyRole("USER", "ANONYMOUS")
                            .anyRequest().permitAll();
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler)
                            .authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionHandlerFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(accessDeniedFilter, AuthorizationFilter.class);

        return http.build();
    }

    protected CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getDefaultCorsConfiguration());
        return source;
    }

    private CorsConfiguration getDefaultCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:8080",
                        "http://localhost:3000",
                        "https://lets-intern.vercel.app",
                        SERVER_URL
                )
        );
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        return configuration;
    }
}
