package com.thiagoaio.api.user

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping('/users')
class UserController {

    @Autowired
    UserService userService

    @GetMapping('/{email}')
    UserModel getById(@PathVariable("email") String email) {
        userService.getByEmail(email)
    }
}