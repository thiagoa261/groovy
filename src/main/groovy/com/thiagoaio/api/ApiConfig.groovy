package com.thiagoaio.api

import com.thiagoaio.api.auth.AuthGuard
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
class ApiConfig implements WebMvcConfigurer {

    @Autowired
    AuthGuard authGuard

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authGuard).excludePathPatterns("/error");
    }
}