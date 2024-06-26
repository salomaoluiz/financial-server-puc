package com.salomao_neto.financial_server.presentation.auth.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginWithEmailInput(
    @field:Email
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String,
)
