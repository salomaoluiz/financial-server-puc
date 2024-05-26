package com.salomao_neto.financial_server.presentation.financial

import com.salomao_neto.financial_server.application.financial.use_case.*
import com.salomao_neto.financial_server.application.financial.use_case.request.CreateTransactionUseCaseInput
import com.salomao_neto.financial_server.application.financial.use_case.request.EditTransactionUseCaseInput
import com.salomao_neto.financial_server.application.financial.use_case.request.GetTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import com.salomao_neto.financial_server.presentation.financial.request.CreateTransactionInput
import com.salomao_neto.financial_server.presentation.financial.request.EditTransactionInput
import com.salomao_neto.financial_server.presentation.financial.request.GetTransactionsInput
import com.salomao_neto.financial_server.presentation.financial.request.SortBy
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/financial/transaction")
@SecurityRequirement(name = "AuthService")
class TransactionController(
    val getTransactionsUseCase: GetTransactionsUseCase,
    val createTransactionUseCase: CreateTransactionUseCase,
    val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    val editTransactionUseCase: EditTransactionUseCase,
    val deleteTransactionByIdUseCase: DeleteTransactionByIdUseCase
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
    fun getTransactions(
        @Valid input: GetTransactionsInput?
    ): List<TransactionEntity> {
        val useCaseInput = GetTransactionUseCaseInput(
            category = input?.category,
            startDate = input?.startDate,
            endDate = input?.endDate,
            sortBy = if (input?.sortBy != null) SortBy.valueOf(input.sortBy.uppercase()) else null,
        )
        return getTransactionsUseCase.run(useCaseInput)
    }

    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable id: UUID): TransactionEntity {
        return getTransactionByIdUseCase.run(id);
    }

    @PutMapping
    fun editTransaction(
        @Valid @RequestBody input: EditTransactionInput
    ): TransactionEntity {
        val useCaseInput = EditTransactionUseCaseInput(
            id = input.id,
            description = input.description,
            value = input.value,
            date = input.date,
            category = input.category
        )

        return editTransactionUseCase.run(useCaseInput)
    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: UUID): Boolean {
        return deleteTransactionByIdUseCase.run(id)
    }
}
