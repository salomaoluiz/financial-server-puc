package com.salomao_neto.financial_server.presentation.auth

import com.salomao_neto.financial_server.application.auth.use_case.LoginWithEmailUseCase
import com.salomao_neto.financial_server.application.auth.use_case.RegisterNewUserUseCase
import com.salomao_neto.financial_server.application.auth.use_case.request.LoginWithEmailUseCaseInput
import com.salomao_neto.financial_server.application.auth.use_case.request.RegisterNewUserUseCaseInput
import com.salomao_neto.financial_server.domain.auth.AuthEntity
import com.salomao_neto.financial_server.presentation.auth.request.LoginWithEmailInput
import com.salomao_neto.financial_server.presentation.auth.request.RegisterNewUserInput
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")

class AuthController(
    val registerNewUserUseCase: RegisterNewUserUseCase,
    val loginWithEmailUseCase: LoginWithEmailUseCase
) {

    @PostMapping("/login-with-email")
    fun loginWithEmail(@RequestBody @Valid input: LoginWithEmailInput): ResponseEntity<AuthEntity> {
        val useCaseInput = LoginWithEmailUseCaseInput(email = input.email, password = input.password);
        loginWithEmailUseCase.run(useCaseInput).let {
            if (it == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
            }

            return ResponseEntity.ok(it)
        }
    }

    @PostMapping("/register")

    fun registerNewUser(@RequestBody @Valid input: RegisterNewUserInput): ResponseEntity<AuthEntity> {
        val useCaseInput =
            RegisterNewUserUseCaseInput(name = input.name, email = input.email, password = input.password)

        registerNewUserUseCase.run(useCaseInput).let {
            return ResponseEntity.ok(it)
        }
    }
}
