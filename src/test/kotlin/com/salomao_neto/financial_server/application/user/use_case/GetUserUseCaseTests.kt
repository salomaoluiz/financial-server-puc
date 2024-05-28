package com.salomao_neto.financial_server.application.user.use_case

import AuthorizationSetup
import com.salomao_neto.financial_server.application.user.repository.UserRepository
import com.salomao_neto.financial_server.domain.user.UserEntity
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.Test


class GetUserUseCaseTests : AuthorizationSetup() {
    private val userRepository = mockk<UserRepository>()
    private val getUserUseCase = GetUserUseCase(userRepository)

    @Test
    fun `should call user repository with the userId`() {
        every { userRepository.findUserById(any()) } returns null

        val result = getUserUseCase.run()

        verify { userRepository.findUserById(userUUID) }
        result shouldBe null
    }

    @Test
    fun `should return the UserEntity returned by userRepository`() {
        val userEntity = UserEntity(id = userUUID, name = "User test", email = "teste@gmail.com")
        every { userRepository.findUserById(any()) } returns userEntity

        val result = getUserUseCase.run()

        result shouldBe userEntity
    }
}
