package com.thiagoaio.api.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthGuard implements HandlerInterceptor {

    @Autowired
    private AuthService authService

    @Override
    boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = handler as HandlerMethod

            boolean isPublic = method.hasMethodAnnotation(PublicRoute) || method.beanType.isAnnotationPresent(PublicRoute)

            if (isPublic) return true;
        }

        String token = request.getHeader("Authorization");
        if (!token) return reject(response, HttpStatus.BAD_REQUEST, "Token de autenticação não fornecido.");

        String userId = authService.validateToken(token);
        if (!userId) return reject(response, HttpStatus.UNAUTHORIZED, "Token inválido ou expirado.");

        request.setAttribute("userId", UUID.fromString(userId))
        return true
    }

    private static boolean reject(
            HttpServletResponse response,
            HttpStatus status,
            String message
    ) {
        response.contentType = "text/plain;charset=UTF-8"
        response.characterEncoding = "UTF-8"
        response.status = status.value()
        response.writer.write(message)
        response.writer.flush()
        return false
    }
}
