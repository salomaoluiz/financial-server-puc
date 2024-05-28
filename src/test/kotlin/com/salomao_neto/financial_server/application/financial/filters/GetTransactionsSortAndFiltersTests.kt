package com.salomao_neto.financial_server.application.financial.filters

import com.salomao_neto.financial_server.application.financial.use_case.request.GetTransactionUseCaseInput
import com.salomao_neto.financial_server.domain.financial.TransactionEntity
import com.salomao_neto.financial_server.presentation.financial.request.SortBy
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

class GetTransactionsSortAndFiltersTests {

    private val transactions = listOf(
        TransactionEntity(
            id = UUID.fromString("0f21bf44-9911-4386-86ba-815a6d93c0bf"),
            description = "Rice", date = "2024-05-22T00:00:00Z", value = "10.50", category = "Market",
        ),
        TransactionEntity(
            id = UUID.fromString("6220dc34-6322-4a88-af54-e659b80e2ed5"),
            description = "Gasoline", date = "2024-05-19T00:00:00Z", value = "-10.50", category = "Fuel",
        ),
        TransactionEntity(
            id = UUID.fromString("6220dc34-6322-4a88-af54-e659b80e2ed5"),
            description = "Gasoline", date = "2024-05-25T00:00:00Z", value = "15.50", category = "Fuel",
        ),
    )


    @Test
    fun `should filter by start date`() {
        val filter = GetTransactionUseCaseInput(
            startDate = "2024-05-24T00:00:00Z",
            sortBy = null,
            endDate = null,
            category = null
        )

        val result = GetTransactionsSortAndFilters(filter, transactions).filterByStartDate().build()

        result shouldBe listOf(transactions[2])
    }

    @Test
    fun `should filter by end date`() {
        val filter = GetTransactionUseCaseInput(
            startDate = null,
            sortBy = null,
            endDate = "2024-05-20T00:00:00Z",
            category = null
        )

        val result = GetTransactionsSortAndFilters(filter, transactions).filterByEndDate().build()

        result shouldBe listOf(transactions[1])
    }

    @Test
    fun `should filter by category`() {
        val filter = GetTransactionUseCaseInput(
            startDate = null,
            sortBy = null,
            endDate = null,
            category = "Market"
        )

        val result = GetTransactionsSortAndFilters(filter, transactions).filterByCategory().build()

        result shouldBe listOf(transactions[0])
    }

    @Test
    fun `should sort by date asc`() {
        val filter = GetTransactionUseCaseInput(
            startDate = null,
            sortBy = SortBy.DATE_ASC,
            endDate = null,
            category = null
        )

        val result = GetTransactionsSortAndFilters(filter, transactions).sort().build()

        result shouldBe listOf(transactions[1], transactions[0], transactions[2])
    }


}
