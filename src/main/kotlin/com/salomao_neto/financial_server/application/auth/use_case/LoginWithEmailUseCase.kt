package com.salomao_neto.financial_server.application.auth.use_case

import com.salomao_neto.financial_server.application.auth.repository.AuthRepository
import com.salomao_neto.financial_server.application.auth.use_case.request.LoginWithEmailUseCaseInput
import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.domain.auth.AuthEntity
import org.springframework.stereotype.Service

@Service
class LoginWithEmailUseCase(private val authRepository: AuthRepository) :
    UseCaseWithInput<LoginWithEmailUseCaseInput, AuthEntity?> {
    override fun run(input: LoginWithEmailUseCaseInput): AuthEntity? {
        val auth = this.authRepository.loginWithEmail(input.email, input.password)
        return auth
    }
}
