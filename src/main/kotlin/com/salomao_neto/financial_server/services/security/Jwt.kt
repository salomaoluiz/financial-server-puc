package com.salomao_neto.financial_server.services.security

import com.salomao_neto.financial_server.domain.user.UserEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

@Component
class Jwt {
    fun createToken(user: UserEntity): String {
        return UserToken(user).let {
            Jwts.builder().signWith(Keys.hmacShaKeyFor(SECRET.toByteArray())).json(JacksonSerializer())
                .issuedAt(utcNow().toDate())
                .expiration(utcNow().plusHours(EXPIRE_HOURS).toDate()).issuer(ISSUER)
                .subject(user.id.toString()).claim(
                    USER_FIELD, it
                ).compact()
        }

    }

    fun extract(req: HttpServletRequest): Authentication? {
        try {
            val header = req.getHeader(HttpHeaders.AUTHORIZATION)

            if (header == null || !header.startsWith("Bearer")) {
                return null
            }

            val token = header.replace("Bearer ", "").trim()

            val claims = Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.toByteArray()))
                .json(JacksonDeserializer(mapOf(USER_FIELD to UserToken::class.java)))
                .build()
                .parseSignedClaims(token).payload

            if (claims.issuer != ISSUER) return null

            val user = claims.get("user", UserToken::class.java)

            return user.toAuthentication()
        } catch (e: Throwable) {
            log.debug("Token rejected", e)
            return null
        }
    }

    companion object {
        const val SECRET = "0cd4d54502b2d8489a8bc338c4f4322b26add194f4e4ad5de8c1ec0a10b22244"
        const val EXPIRE_HOURS = 2L
        const val ISSUER = "AuthServer"
        const val USER_FIELD = "user"
        private fun utcNow() = ZonedDateTime.now(ZoneOffset.UTC)
        private fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())

        private val log = LoggerFactory.getLogger(Jwt::class.java)
        private fun UserToken.toAuthentication(): Authentication {
            return UsernamePasswordAuthenticationToken.authenticated(this, id, null)
        }
    }
}
