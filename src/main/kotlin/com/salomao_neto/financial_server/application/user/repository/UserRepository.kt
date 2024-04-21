package com.salomao_neto.financial_server.application.user.repository

import com.salomao_neto.financial_server.application.user.use_case.NewUserInput
import com.salomao_neto.financial_server.domain.user.UserEntity

interface UserRepository {
    fun save(user: NewUserInput): UserEntity;
}
