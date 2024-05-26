package com.salomao_neto.financial_server.presentation.financial.request

import com.salomao_neto.financial_server.presentation.error.validations.Date
import com.salomao_neto.financial_server.presentation.error.validations.Select
import org.springframework.web.bind.annotation.RequestParam

enum class SortBy {
    DATE_DESC,
    DATE_ASC,
    DESCRIPTION_DESC,
    DESCRIPTION_ASC,
    CATEGORY_DESC,
    CATEGORY_ASC,
    VALUE_DESC,
    VALUE_ASC,
}

class GetTransactionsInput(
    @RequestParam
    val category: String?,

    @RequestParam
    @field:Date
    val startDate: String?,

    @RequestParam
    @field:Date
    val endDate: String?,

    @RequestParam
    @field:Select(enumClass = SortBy::class, message="Must be one of {enumValues}")
    val sortBy: String?
)
