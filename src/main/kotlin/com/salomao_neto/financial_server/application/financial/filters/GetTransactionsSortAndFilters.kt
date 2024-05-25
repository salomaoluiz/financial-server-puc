package com.salomao_neto.financial_server.application.financial.filters

import com.salomao_neto.financial_server.application.financial.use_case.request.GetTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import com.salomao_neto.financial_server.presentation.financial.request.SortBy
import java.time.Instant
import java.time.format.DateTimeFormatter

class GetTransactionsSortAndFilters(
    private val filters: GetTransactionUseCaseInput,
    private val transactions: List<TransactionEntity>
) {
    private var filteredTransactions: List<TransactionEntity> = transactions

    private fun parseDate(date: String?): Instant {
        return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(date))
    }

    fun filterByStartDate(): GetTransactionsSortAndFilters {
        if (filters.startDate == null) {
            return this
        }

        filteredTransactions = filteredTransactions.filter {
            val filterDate = parseDate(filters.startDate)
            val transactionDate = parseDate(it.date)

            val isBefore = filterDate.isBefore(transactionDate)

            isBefore
        }

        return this
    }

    fun filterByEndDate(): GetTransactionsSortAndFilters {
        if (filters.endDate == null) {
            return this
        }

        filteredTransactions = filteredTransactions.filter {
            val filterDate = parseDate(filters.endDate)
            val transactionDate = parseDate(it.date)

            val isAfter = filterDate.isAfter(transactionDate)

            isAfter
        }

        return this
    }

    fun filterByCategory(): GetTransactionsSortAndFilters {
        if (filters.category == null) {
            return this
        }

        filteredTransactions = filteredTransactions.filter {
            it.category == filters.category
        }

        return this
    }

    fun sort(): GetTransactionsSortAndFilters {
        if (filters.sortBy == null) {
            return this
        }

        filteredTransactions = when (filters.sortBy) {
            SortBy.DATE_ASC -> filteredTransactions.sortedBy { parseDate(it.date) }
            SortBy.DATE_DESC -> filteredTransactions.sortedByDescending { parseDate(it.date) }
        }

        return this
    }

    fun build(): List<TransactionEntity> {
        return filteredTransactions
    }
}
