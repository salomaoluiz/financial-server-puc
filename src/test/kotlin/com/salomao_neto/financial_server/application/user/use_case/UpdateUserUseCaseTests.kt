package com.salomao_neto.financial_server.application.user.use_case

import AuthorizationSetup
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.application.user.use_case.request.UpdateUserUseCaseInput
import com.salomao_neto.financial_server.domain.user.UserEntity
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class UpdateUserUseCaseTests: AuthorizationSetup() {
    private val userRepository = mockk<UserRepository>()
    private val updateUserUseCase = UpdateUserUseCase(userRepository)

    @Test
    fun `should request with the correct params and return the user entity`() {
        val userEntity = UserEntity(name="Test Updated", email = "new-email-test@gmail.com", id = userUUID)
        every { userRepository.updateUser(any(), any()) } returns userEntity

        val useCaseInput = UpdateUserUseCaseInput(name = "Test Updated", email = "new-email-test@gmail.com", password = "new-password")
        val result = updateUserUseCase.run(useCaseInput)

        verify { userRepository.updateUser(userUUID, useCaseInput) }
        result shouldBeEqual userEntity

    }
}
