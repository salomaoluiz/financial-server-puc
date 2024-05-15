package com.salomao_neto.financial_server.presentation.error.validations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValueValidator::class])
annotation class Value(
    val message: String = "This value is invalid, should be like -10.20",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)


class ValueValidator : ConstraintValidator<Value, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        val digitRegex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return value.matches(digitRegex)
    }
}
