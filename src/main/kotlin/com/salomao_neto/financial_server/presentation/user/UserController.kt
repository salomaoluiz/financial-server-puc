package com.salomao_neto.financial_server.presentation.user

import com.salomao_neto.financial_server.application.user.use_case.GetUserUseCase
import com.salomao_neto.financial_server.application.user.use_case.UpdateUserUseCase
import com.salomao_neto.financial_server.application.user.use_case.request.UpdateUserUseCaseInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import com.salomao_neto.financial_server.presentation.user.request.UpdateUserInput
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "AuthService")
class UserController(
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase
) {

    @GetMapping()
    fun getUser(): UserEntity? {
        return getUserUseCase.run()
    }

    @PutMapping()
    fun updateUser(@RequestBody @Valid input: UpdateUserInput): UserEntity {
        val useCaseInput = UpdateUserUseCaseInput(
            email = input.email,
            name = input.name,
            password = input.password,
        )

        return updateUserUseCase.run(useCaseInput)
    }
}
