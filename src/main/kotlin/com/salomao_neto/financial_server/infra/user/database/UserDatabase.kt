package com.salomao_neto.financial_server.infra.user.database

import com.salomao_neto.financial_server.presentation.user.request.NewUserInput
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserDatabase : JpaRepository<User, UUID> 
