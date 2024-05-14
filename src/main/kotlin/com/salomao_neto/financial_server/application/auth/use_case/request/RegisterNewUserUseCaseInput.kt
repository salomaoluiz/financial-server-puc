package com.salomao_neto.financial_server.application.auth.use_case.request


data class RegisterNewUserUseCaseInput(
    val email: String,
    val password: String,
    val name: String
)
