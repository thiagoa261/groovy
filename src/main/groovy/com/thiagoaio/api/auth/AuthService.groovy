package com.thiagoaio.api.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService {

    String login() {
        return "logged in";
    }
}
