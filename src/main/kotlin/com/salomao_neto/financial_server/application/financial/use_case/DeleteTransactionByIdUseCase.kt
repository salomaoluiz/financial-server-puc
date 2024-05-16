package com.salomao_neto.financial_server.application.financial.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteTransactionByIdUseCase(private val transactionRepository: TransactionRepository) :
    UseCaseWithInput<UUID, Boolean> {
    override fun run(input: UUID): Boolean {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        val userId = authentication.credentials as UUID

        return this.transactionRepository.deleteTransactionById(userId, input)
    }
}
