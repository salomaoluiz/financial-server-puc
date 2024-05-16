package com.salomao_neto.financial_server.application.financial.repository

import com.salomao_neto.financial_server.application.financial.use_case.request.CreateTransactionUseCaseInput
import com.salomao_neto.financial_server.application.financial.use_case.request.EditTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import java.util.UUID

interface TransactionRepository {
    fun createTransaction(userId: UUID, input: CreateTransactionUseCaseInput): TransactionEntity

    fun getTransactionById(id: UUID): TransactionEntity

    fun getTransactions(userId: UUID): List<TransactionEntity>

    fun editTransaction(userId: UUID, input: EditTransactionUseCaseInput): TransactionEntity

    fun deleteTransactionById(userId: UUID, id: UUID): Boolean
}
