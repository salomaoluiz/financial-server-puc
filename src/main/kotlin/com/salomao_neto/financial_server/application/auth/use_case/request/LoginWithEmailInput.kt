package com.salomao_neto.financial_server.application.auth.use_case.request

data class LoginWithEmailUseCaseInput(
    val email: String,
    val password: String,
)
