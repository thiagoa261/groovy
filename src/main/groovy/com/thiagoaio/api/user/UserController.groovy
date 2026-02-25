package com.thiagoaio.api.user

import com.thiagoaio.api.user.dto.UserDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping('/users')
class UserController {

    @Autowired
    private UserService userService

    @PostMapping()
    public createUser(@Valid @RequestBody UserDto body) {
        return userService.createUser(body);
    }
}