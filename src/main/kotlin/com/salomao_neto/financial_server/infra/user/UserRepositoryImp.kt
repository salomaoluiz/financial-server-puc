package com.salomao_neto.financial_server.infra.user

import com.salomao_neto.financial_server.application.auth.use_case.request.RegisterNewUserUseCaseInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.application.user.use_case.request.UpdateUserUseCaseInput
import com.salomao_neto.financial_server.infra.user.database.User
import com.salomao_neto.financial_server.infra.user.database.UserDatabase
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.optionals.getOrElse

@Component
class UserRepositoryImp(private val userDatabase: UserDatabase) : UserRepository {

    override fun registerUser(newUser: RegisterNewUserUseCaseInput): UserEntity {

        val user =
            User(name = newUser.name, email = newUser.email, password = newUser.password, transactions = ArrayList())

        userDatabase.save(user)

        return UserEntity(id = user.id, email = user.email, name = user.name)
    }

    override fun findUserById(uuid: UUID): UserEntity? {
        val userEntity = userDatabase.findById(uuid)

        if (userEntity.isEmpty) {
            return null
        }

        val user = userEntity.get()

        return UserEntity(id = user.id, email = user.email, name = user.name)
    }

    override fun updateUser(id: UUID, input: UpdateUserUseCaseInput): UserEntity {
        val user = userDatabase.findById(id).getOrElse {
            throw ChangeSetPersister.NotFoundException()
        }

        user.email = input.email ?: user.email
        user.name = input.name ?: user.name
        user.password = input.password ?: user.password

        userDatabase.save(user)

        return UserEntity(id = user.id, email = user.email, name = user.name)
    }

}
