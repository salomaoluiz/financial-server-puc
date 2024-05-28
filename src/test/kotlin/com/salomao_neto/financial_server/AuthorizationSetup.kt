import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import java.util.UUID

open class AuthorizationSetup {
    val userUUID: UUID = UUID.fromString("ef0f3f4e-2a85-465b-bc11-6e6e373ba6d1")


    @BeforeEach
    fun setupAuthorization() {
        clearAllMocks()

        mockkStatic(SecurityContextHolder::class)

        val securityContext = mockk<SecurityContext>()
        val authentication = mockk<Authentication>()

        every { SecurityContextHolder.getContext() } returns securityContext
        every { securityContext.authentication } returns authentication
        every { authentication.credentials } returns userUUID
    }

    @AfterEach
    fun cleanUpAuthorization() {
        unmockkAll()
        checkUnnecessaryStub()
    }
}
