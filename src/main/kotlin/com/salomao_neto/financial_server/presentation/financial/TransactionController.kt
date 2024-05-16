package com.salomao_neto.financial_server.presentation.financial

import com.salomao_neto.financial_server.application.financial.use_case.*
import com.salomao_neto.financial_server.application.financial.use_case.request.CreateTransactionUseCaseInput
import com.salomao_neto.financial_server.application.financial.use_case.request.EditTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import com.salomao_neto.financial_server.presentation.financial.request.CreateTransactionInput
import com.salomao_neto.financial_server.presentation.financial.request.EditTransactionInput
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
    fun getTransactions(): List<TransactionEntity> {
        return getTransactionsUseCase.run()
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
