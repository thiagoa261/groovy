package com.thiagoaio.api.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private UserRepository userRepository

    UserModel getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}