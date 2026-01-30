package com.thiagoaio.api.auth

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping('/auth')
class AuthController {

    AuthService authService

    @PostMapping('/login')
    login(@RequestBody request) {
        authService.login();
    }
}

