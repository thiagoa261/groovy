package com.thiagoaio.api.auth

import com.thiagoaio.api.auth.dto.LoginDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping('/auth')
class AuthController {

    @Autowired
    AuthService authService

    @PublicRoute
    @PostMapping('/login')
    public login(@Valid @RequestBody LoginDto body) {
        return authService.login(body);
    }
}

