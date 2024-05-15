package com.salomao_neto.financial_server.presentation.financial

import com.salomao_neto.financial_server.application.financial.use_case.*
import com.salomao_neto.financial_server.application.financial.use_case.request.CreateTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import com.salomao_neto.financial_server.presentation.financial.request.CreateTransactionInput
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/financial/transaction")
@SecurityRequirement(name = "AuthService")
class TransactionController(
    val getTransactionsUseCase: GetTransactionsUseCase,
    val createTransactionUseCase: CreateTransactionUseCase,
) {

    @PostMapping
    fun createTransaction(@Valid @RequestBody input: CreateTransactionInput): TransactionEntity {
        val useCaseInput = CreateTransactionUseCaseInput(
            description = input.description,
            value = input.value,
            category = input.category,
            date = input.date
        )

        return createTransactionUseCase.run(useCaseInput)
    }

    @GetMapping()
    fun getTransactions(): List<TransactionEntity> {
        return getTransactionsUseCase.run()
    }
}
