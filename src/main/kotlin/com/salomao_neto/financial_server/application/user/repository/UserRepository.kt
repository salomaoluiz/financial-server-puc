package com.salomao_neto.financial_server.application.user.repository

import com.salomao_neto.financial_server.domain.user.UserEntity
import com.salomao_neto.financial_server.presentation.user.request.NewUserInput
import java.util.UUID

interface UserRepository {
    fun save(newUser: NewUserInput): UserEntity
    fun findUserById(uuid: UUID): UserEntity?
}
