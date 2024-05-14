package com.salomao_neto.financial_server.application.user.repository

import com.salomao_neto.financial_server.application.auth.use_case.request.RegisterNewUserUseCaseInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import java.util.UUID

interface UserRepository {
    fun registerUser(newUser: RegisterNewUserUseCaseInput): UserEntity
    fun findUserById(uuid: UUID): UserEntity?
}
