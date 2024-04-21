package com.salomao_neto.financial_server.application.user.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.domain.user.UserEntity
import org.springframework.stereotype.Service

data class NewUserInput(val email: String = "", val password: String = "", val name: String = "") {}

@Service
class CreateNewUserUseCase(private val userRepository: UserRepository) : UseCaseWithInput<NewUserInput, UserEntity> {

    override fun run(input: NewUserInput): UserEntity {
        val user = this.userRepository.save(input);

        return user;
    }

}
