package com.salomao_neto.financial_server.application.user.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.domain.user.UserEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetUserByIdUseCase(private val userRepository: UserRepository) : UseCaseWithInput<UUID, UserEntity?> {
    override fun run(input: UUID): UserEntity? {
        return userRepository.findUserById(input)
    }
}
