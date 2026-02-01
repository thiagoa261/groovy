package com.thiagoaio.api.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class LoginDto {

    @NotBlank(message = 'Email é obrigatório')
    @Email(message = 'Email inválido')
    String email

    @NotBlank(message = 'Senha é obrigatória')
    String password
}
