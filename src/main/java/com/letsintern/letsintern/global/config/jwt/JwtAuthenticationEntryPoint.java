package com.letsintern.letsintern.global.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import com.letsintern.letsintern.global.error.ErrorResponse;
import com.letsintern.letsintern.global.error.exception.NoTokenException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        responseToClient(response, getErrorResponse(NoTokenException.EXCEPTION.getErrorCode()));
    }

    private ErrorResponse getErrorResponse(BaseErrorCode baseErrorCode) {
        return ErrorResponse.from(baseErrorCode.getErrorReason());
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(Integer.parseInt(errorResponse.getStatus()));
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
