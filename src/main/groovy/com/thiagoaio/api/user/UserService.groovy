package com.thiagoaio.api.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.thiagoaio.api.utils.Utils

@Service
class UserService {

    @Autowired
    private UserRepository userRepository

    ResponseEntity<?> createUser(UserModel user) {
        try {
            var existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");

            user.setPassword(Utils.hashString(user.getPassword()));

            var newUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}