package com.salomao_neto.financial_server.domain.financial

import java.util.UUID

data class TransactionEntity(
    val id: UUID?,
    val description: String?,
    val date: String?,
    val value: String?,
    val category: String?
)
