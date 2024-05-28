package com.salomao_neto.financial_server.application.financial.use_case

import AuthorizationSetup
import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import com.salomao_neto.financial_server.application.financial.use_case.request.CreateTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.Test


class CreateTransactionUseCaseTests : AuthorizationSetup() {
    private val transactionRepository = mockk<TransactionRepository>()
    private val createTransactionUseCase = CreateTransactionUseCase(transactionRepository)
    private val transactionEntity = mockk<TransactionEntity>()
    private val useCaseInput = CreateTransactionUseCaseInput(
        description = "description",
        date = "date",
        value = "value",
        category = "category",
    )

    @Test
    fun `should call transaction repository repository with correct params`() {
        every { transactionRepository.createTransaction(any(), any()) } returns transactionEntity

        val result = createTransactionUseCase.run(useCaseInput)

        verify { transactionRepository.createTransaction(userUUID, useCaseInput) }
        result shouldBe transactionEntity
    }
}
