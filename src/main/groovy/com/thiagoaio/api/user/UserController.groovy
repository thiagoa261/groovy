package com.thiagoaio.api.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping('/users')
class UserController {

    @Autowired
    private UserService userService

    @PostMapping()
    public createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }
}