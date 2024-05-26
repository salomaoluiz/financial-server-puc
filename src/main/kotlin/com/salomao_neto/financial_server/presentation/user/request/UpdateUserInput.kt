package com.salomao_neto.financial_server.presentation.user.request

import jakarta.validation.constraints.Email

class UpdateUserInput(
    @Email
    val email: String?,
    val name: String?,
    val password: String?
)
