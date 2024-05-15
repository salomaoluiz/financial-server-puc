package com.salomao_neto.financial_server.presentation.error

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
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

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(exception: NotFoundException): String {
        return "Not Found"
    }
}
