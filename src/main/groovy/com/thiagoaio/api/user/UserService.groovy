package com.thiagoaio.api.user

import com.thiagoaio.api.user.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.thiagoaio.api.utils.Utils

@Service
class UserService {

    @Autowired
    private UserRepository userRepository

    UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    UserModel findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    ResponseEntity<?> createUser(UserDto user) {
        try {
            var existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");

            UserModel userModel = new UserModel(
                    name: user.getName(),
                    email: user.getEmail(),
                    password: Utils.hashString(user.getPassword()),
                    active: false,
            )

            var newUser = userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}