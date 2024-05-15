package com.salomao_neto.financial_server.application.financial.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithoutInput
import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetTransactionsUseCase(private val transactionRepository: TransactionRepository) :
    UseCaseWithoutInput<List<TransactionEntity>> {
    override fun run(): List<TransactionEntity> {
        val auth = SecurityContextHolder.getContext().authentication

        val userId = auth.credentials as UUID
        return this.transactionRepository.getTransactions(userId)
    }
}
