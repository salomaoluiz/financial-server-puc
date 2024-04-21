package com.salomao_neto.financial_server.presentation.user

import com.salomao_neto.financial_server.application.user.use_case.CreateNewUserUseCase
import com.salomao_neto.financial_server.application.user.use_case.NewUserInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    val createNewUserUseCase: CreateNewUserUseCase
) {

    @PostMapping()
    fun save(input: NewUserInput): UserEntity {
        return createNewUserUseCase.run(input);
    }
}
