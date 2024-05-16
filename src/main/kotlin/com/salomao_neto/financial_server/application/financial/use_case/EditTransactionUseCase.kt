package com.salomao_neto.financial_server.application.financial.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import com.salomao_neto.financial_server.application.financial.use_case.request.EditTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class EditTransactionUseCase(private val transactionRepository: TransactionRepository) :
    UseCaseWithInput<EditTransactionUseCaseInput, TransactionEntity> {
    override fun run(input: EditTransactionUseCaseInput): TransactionEntity {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val userId = authentication.credentials as UUID

        return this.transactionRepository.editTransaction(userId, input)
    }
}
