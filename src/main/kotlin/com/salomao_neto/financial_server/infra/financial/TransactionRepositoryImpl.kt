package com.salomao_neto.financial_server.infra.financial

import com.salomao_neto.financial_server.application.financial.repository.TransactionRepository
import com.salomao_neto.financial_server.application.financial.use_case.request.CreateTransactionUseCaseInput
import com.salomao_neto.financial_server.application.financial.use_case.request.EditTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import com.salomao_neto.financial_server.infra.financial.database.Transaction
import com.salomao_neto.financial_server.infra.financial.database.TransactionDatabase
import com.salomao_neto.financial_server.infra.user.database.UserDatabase
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Component
class TransactionRepositoryImpl(
    private val transactionDatabase: TransactionDatabase,
    private val userDatabase: UserDatabase
) : TransactionRepository {

    private fun getEntityByDatabase(transaction: Transaction): TransactionEntity {
        return TransactionEntity(
            id = transaction.id,
            description = transaction.description,
            category = transaction.category,
            date = transaction.date,
            value = transaction.value,
        )
    }

    override fun createTransaction(id: UUID, input: CreateTransactionUseCaseInput): TransactionEntity {

        val user = userDatabase.findById(id).getOrElse {
            throw Exception("User not found")
        }


        val newTransaction = Transaction(
            description = input.description,
            category = input.category,
            date = input.date,
            value = input.value,
            user = user
        )

        transactionDatabase.save(newTransaction)

        return getEntityByDatabase(newTransaction)
    }

    override fun getTransactionById(id: UUID): TransactionEntity {
        val transaction = transactionDatabase.findById(id).getOrElse {
            throw NotFoundException()
        }

        return getEntityByDatabase(transaction)
    }

    override fun getTransactions(id: UUID): List<TransactionEntity> {
        val transactions = transactionDatabase.findByUserId(id)

        return transactions.map {
            getEntityByDatabase(it)
        }
    }

    override fun editTransaction(id: UUID, input: EditTransactionUseCaseInput): TransactionEntity {
        TODO("Not yet implemented")
    }

    override fun deleteTransactionById(id: UUID): Boolean {
        TODO("Not yet implemented")
    }
}
