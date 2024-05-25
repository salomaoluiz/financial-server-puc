package com.salomao_neto.financial_server.application.financial.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.financial.filters.GetTransactionsSortAndFilters
import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import com.salomao_neto.financial_server.application.financial.use_case.request.GetTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetTransactionsUseCase(private val transactionRepository: TransactionRepository) :
    UseCaseWithInput<GetTransactionUseCaseInput, List<TransactionEntity>> {
    override fun run(input: GetTransactionUseCaseInput): List<TransactionEntity> {
        val auth = SecurityContextHolder.getContext().authentication

        val userId = auth.credentials as UUID
        val transactions = this.transactionRepository.getTransactions(userId)

        val filteredTransactions = GetTransactionsSortAndFilters(input, transactions)
            .filterByStartDate()
            .filterByEndDate()
            .filterByCategory()
            .sort()
            .build()


        return filteredTransactions
    }
}
