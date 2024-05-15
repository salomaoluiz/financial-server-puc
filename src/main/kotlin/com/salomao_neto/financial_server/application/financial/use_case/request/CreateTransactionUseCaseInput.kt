package com.salomao_neto.financial_server.application.financial.use_case.request

data class CreateTransactionUseCaseInput(
    val description: String,
    val date: String,
    val value: String,
    val category: String
)
