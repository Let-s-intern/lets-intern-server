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

    private final String[] AdminGetPatterns = {
            "/user/admin/**", "/memo/**", "/program/admin/**", "/faq/**", "/application/admin/**", "/review/admin/**"
    };

    private final String[] AdminPostPatterns = {
            "/memo/**", "/program", "/faq/**"
    };

    private final String[] AdminPatchPatterns = {
            "/user/admin/**", "/memo/**", "/program/**", "/faq/**", "/application/**", "/review/**"
    };

    private final String[] AdminDeletePatterns = {
            "/memo/**", "/program/**", "/faq/**", "/user/admin/**"
    };

    private final String[] UserGetPatterns = {
            "/user", "/user/withdraw", "/user/detail-info", "/application", "/review/**"
    };

    private final String[] UserPostPatterns = {
            "/user/signout", "/review/**"
    };

    private final String[] UserPatchPatterns = {
            "/user", "/user/password"
    };

    private final String[] UserDeletePatterns = {
            "/application/**"
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
                            .requestMatchers(SwaggerPatterns).permitAll()
                            .requestMatchers(HttpMethod.GET, UserGetPatterns).hasAnyRole("USER", "ANONYMOUS","ADMIN")
                            .requestMatchers(HttpMethod.POST, UserPostPatterns).hasAnyRole("USER", "ANONYMOUS","ADMIN")
                            .requestMatchers(HttpMethod.PATCH, UserPatchPatterns).hasAnyRole("USER", "ANONYMOUS","ADMIN")
                            .requestMatchers(HttpMethod.DELETE, UserDeletePatterns).hasAnyRole("USER", "ANONYMOUS","ADMIN")
                            .requestMatchers(HttpMethod.GET, AdminGetPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, AdminPostPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.PATCH, AdminPatchPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, AdminDeletePatterns).hasAnyRole("ADMIN")
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
                        "https://letsintern.co.kr",
                        "https://www.letsintern.co.kr",
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
