package com.salomao_neto.financial_server.presentation.error.validations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.time.Instant
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DateValidator::class])
annotation class Date(
    val message: String = "This date is invalid, should be like 2024-05-10T22:10:00Z",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)


class DateValidator : ConstraintValidator<Date, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        try {
            Instant.from(DateTimeFormatter.ISO_INSTANT.parse(value))
            return true
        } catch (e: Throwable) {
            return false
        }
    }
}
