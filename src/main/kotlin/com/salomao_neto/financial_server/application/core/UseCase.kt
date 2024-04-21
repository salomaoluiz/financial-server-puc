package com.salomao_neto.financial_server.application.core

interface UseCaseWithInput<I, R> {
    fun run(input: I): R
}

interface UseCaseWithoutInput<R> {
    fun run(): R;
}
