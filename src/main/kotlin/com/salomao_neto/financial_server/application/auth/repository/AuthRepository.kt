package com.salomao_neto.financial_server.application.auth.repository

import com.salomao_neto.financial_server.domain.auth.AuthEntity

interface AuthRepository {
    fun loginWithEmail(email: String, password: String): AuthEntity?
}
