package com.salomao_neto.financial_server.presentation.error

import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.HashMap

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentException(exception: MethodArgumentNotValidException): Map<String, String> {
        val errors = HashMap<String, String>()

        exception.bindingResult.fieldErrors.forEach { error ->
            error.defaultMessage?.let { errors[error.field] = it }
        }

        return errors;
    }
}
