package com.salomao_neto.financial_server.presentation.financial.request

import com.salomao_neto.financial_server.presentation.error.validations.Date
import com.salomao_neto.financial_server.presentation.error.validations.Value
import jakarta.validation.constraints.NotBlank

data class CreateTransactionInput(
    @field:NotBlank(message = "The field description is required")
    val description: String,

    @field:NotBlank(message = "The field date is required")
    @field:Date
    val date: String,

    @field:NotBlank(message = "The field value is required")
    @field:Value
    val value: String,

    @field:NotBlank(message = "The field category is required")
    val category: String
)
