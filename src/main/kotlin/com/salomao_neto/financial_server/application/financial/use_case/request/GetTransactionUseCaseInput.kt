package com.salomao_neto.financial_server.application.financial.use_case.request

import com.salomao_neto.financial_server.presentation.financial.request.SortBy

class GetTransactionUseCaseInput(
    val category: String?,
    val startDate: String?,
    val endDate: String?,
    val sortBy: SortBy?
)
