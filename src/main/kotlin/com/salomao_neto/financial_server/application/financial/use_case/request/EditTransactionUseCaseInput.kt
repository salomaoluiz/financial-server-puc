package com.salomao_neto.financial_server.application.financial.use_case.request

import java.util.UUID

data class EditTransactionUseCaseInput(
    val id: UUID,
    val description: String?,
    val date: String?,
    val value: String?,
    val category: String?
)
