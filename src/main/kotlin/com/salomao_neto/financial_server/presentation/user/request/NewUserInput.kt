package com.salomao_neto.financial_server.presentation.user.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class NewUserInput(
    @field:Email
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val name: String) {

}
