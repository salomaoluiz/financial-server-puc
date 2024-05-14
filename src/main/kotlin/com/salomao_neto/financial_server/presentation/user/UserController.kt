package com.salomao_neto.financial_server.presentation.user

import com.salomao_neto.financial_server.application.user.use_case.GetUserUseCase
import com.salomao_neto.financial_server.domain.user.UserEntity
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "AuthService")
class UserController(
    val getUserUseCase: GetUserUseCase
) {

    @GetMapping()
    fun getUser(): UserEntity? {
        return getUserUseCase.run()
    }
}
