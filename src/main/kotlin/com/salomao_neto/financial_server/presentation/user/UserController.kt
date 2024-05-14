package com.salomao_neto.financial_server.presentation.user

import com.salomao_neto.financial_server.application.user.use_case.GetUserByIdUseCase
import com.salomao_neto.financial_server.domain.user.UserEntity
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "AuthService")
class UserController(
    val getUserByIdUseCase: GetUserByIdUseCase
) {

    @GetMapping()
    fun findUserById(@Valid uuid: UUID): UserEntity? {
        return getUserByIdUseCase.run(uuid)
    }
}
