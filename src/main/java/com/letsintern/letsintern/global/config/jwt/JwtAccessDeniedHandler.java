package com.letsintern.letsintern.global.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import com.letsintern.letsintern.global.error.ErrorResponse;
import com.letsintern.letsintern.global.error.exception.ForbiddenUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        responseToClient(response, getErrorResponse(ForbiddenUser.EXCEPTION.getErrorCode()));
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
