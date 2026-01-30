package com.thiagoaio.api

import com.thiagoaio.api.auth.PublicRoute
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {

    @PublicRoute
    @GetMapping("/ok")
    public static String ApiOnline() {
        return "API online!";
    }
}
