package com.salomao_neto.financial_server.application.financial.use_case

import com.salomao_neto.financial_server.application.core.UseCaseWithInput
import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetTransactionByIdUseCase(private val transactionRepository: TransactionRepository) :
    UseCaseWithInput<UUID, TransactionEntity> {
    override fun run(input: UUID): TransactionEntity {
        return this.transactionRepository.getTransactionById(input)
    }
}
