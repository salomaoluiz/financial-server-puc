package com.salomao_neto.financial_server.presentation.financial.request

import com.salomao_neto.financial_server.presentation.error.validations.Date
import com.salomao_neto.financial_server.presentation.error.validations.Value
import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class EditTransactionInput(

    @NotBlank
    val id: UUID,

    @Nullable
    val description: String?,

    @field:Date
    val date: String?,

    @field:Value
    val value: String?,

    val category: String?
)
