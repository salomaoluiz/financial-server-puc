package com.salomao_neto.financial_server.presentation.error.validations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SelectValidator::class])
annotation class Select(
    val message: String = "must be one of {enumClass}",
    val enumClass: KClass<out Enum<*>>,
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val required: Boolean = false
)


class SelectValidator<T> : ConstraintValidator<Select, String> {
    private lateinit var enumValues: Array<out String>
    private lateinit var messageTemplate: String
    private var required: Boolean = false

    override fun initialize(constraintAnnotation: Select) {
        enumValues = constraintAnnotation.enumClass.java.enumConstants.map { it.name }.toTypedArray()
        messageTemplate = constraintAnnotation.message.replace(
            "{enumValues}",
            enumValues.joinToString(", ")
        )
        required = constraintAnnotation.required
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        if ((value == null && !required) || enumValues.contains(value)) {
            return true
        }

        context?.disableDefaultConstraintViolation()
        context?.buildConstraintViolationWithTemplate(messageTemplate)?.addConstraintViolation()

        return false
    }
}
