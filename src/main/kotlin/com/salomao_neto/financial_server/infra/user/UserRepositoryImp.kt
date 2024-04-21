package com.salomao_neto.financial_server.infra.user

import com.salomao_neto.financial_server.application.user.use_case.NewUserInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserRepositoryImp : UserRepository {

    override fun save(user: NewUserInput): UserEntity {
        val id = UUID.randomUUID();
        val name = user.name;
        val email = user.email;
        val password = user.password;

        return UserEntity(id, email, password, name)
    }
}
