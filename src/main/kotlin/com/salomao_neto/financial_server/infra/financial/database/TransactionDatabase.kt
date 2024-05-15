package com.salomao_neto.financial_server.infra.financial.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionDatabase : JpaRepository<Transaction, UUID>
