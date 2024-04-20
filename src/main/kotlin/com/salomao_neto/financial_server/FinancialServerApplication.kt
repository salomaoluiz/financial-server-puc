package com.salomao_neto.financial_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinancialServerApplication

fun main(args: Array<String>) {
    runApplication<FinancialServerApplication>(*args)
}
