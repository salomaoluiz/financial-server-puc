package com.salomao_neto.financial_server.infra.auth

import com.salomao_neto.financial_server.application.auth.repository.AuthRepository
import com.salomao_neto.financial_server.domain.auth.AuthEntity
import com.salomao_neto.financial_server.domain.user.UserEntity
import com.salomao_neto.financial_server.infra.user.database.UserDatabase
import com.salomao_neto.financial_server.services.security.Jwt
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AuthRepositoryImpl(private val userDatabase: UserDatabase) : AuthRepository {
    override fun loginWithEmail(email: String, password: String): AuthEntity? {
        val user = userDatabase.findByEmail(email)

        if (user.password == password) {
            val currentUser = UserEntity(id = user.id, email = user.email, name = user.name)
            val token = Jwt().createToken(currentUser)

            return AuthEntity(token)
        }

        log.warn("User not found")
        return null
    }

    companion object {
        val log = LoggerFactory.getLogger(AuthRepositoryImpl::class.java)
    }
}
