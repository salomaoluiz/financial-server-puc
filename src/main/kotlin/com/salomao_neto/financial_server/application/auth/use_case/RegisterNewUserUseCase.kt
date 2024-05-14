package com.salomao_neto.financial_server.application.auth.use_case

import com.salomao_neto.financial_server.application.auth.use_case.request.LoginWithEmailUseCaseInput
import com.salomao_neto.financial_server.application.auth.use_case.request.RegisterNewUserUseCaseInput
import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.domain.auth.AuthEntity
import org.springframework.stereotype.Service


@Service
class RegisterNewUserUseCase(
    private val userRepository: UserRepository,
    private val loginWithEmailUseCase: LoginWithEmailUseCase
) :
    UseCaseWithInput<RegisterNewUserUseCaseInput, AuthEntity> {

    override fun run(input: RegisterNewUserUseCaseInput): AuthEntity {
        val user = this.userRepository.registerUser(input)

        val loginWithEmailInput = LoginWithEmailUseCaseInput(email = input.email, password = input.password)
        val auth = loginWithEmailUseCase.run(loginWithEmailInput)

        return auth!!;
    }

}
