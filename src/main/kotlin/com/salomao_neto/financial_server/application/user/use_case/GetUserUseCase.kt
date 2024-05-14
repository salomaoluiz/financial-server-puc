package com.salomao_neto.financial_server.application.user.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithoutInput
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.domain.user.UserEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*


@Service
class GetUserUseCase(private val userRepository: UserRepository) : UseCaseWithoutInput<UserEntity?> {
    override fun run(): UserEntity? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val input = authentication.credentials as UUID

        return userRepository.findUserById(input)
    }
}
