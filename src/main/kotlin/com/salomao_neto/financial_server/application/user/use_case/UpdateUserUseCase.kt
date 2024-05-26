package com.salomao_neto.financial_server.application.user.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.application.user.use_case.request.UpdateUserUseCaseInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateUserUseCase(private val userRepository: UserRepository) :
    UseCaseWithInput<UpdateUserUseCaseInput, UserEntity> {
    override fun run(input: UpdateUserUseCaseInput): UserEntity {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val userId = authentication.credentials as UUID

        return userRepository.updateUser(userId, input)
    }


}
