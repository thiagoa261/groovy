package com.thiagoaio.api.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthGuard implements HandlerInterceptor {

    @Override
    boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = handler as HandlerMethod

            boolean isPublic = method.hasMethodAnnotation(PublicRoute) || method.beanType.isAnnotationPresent(PublicRoute)

            if (isPublic) {
                println "ROTA PUBLICA ${request.requestURI}"
                return true
            }
        }

        println "INTERCEPTEI ${request.requestURI}"
        return true // aqui você pode barrar se quiser
    }
}
