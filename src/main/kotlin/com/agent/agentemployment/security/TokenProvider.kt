package com.agent.agentemployment.security

import com.agent.agentemployment.config.TokenConfig
import com.agent.agentemployment.domain.enums.UserRole
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date

private val logger = mu.KotlinLogging.logger {}

@Component
class TokenProvider(
    private val tokenConfig: TokenConfig
) {

    private var secret: String = ""
    private var expiration: Long = 0L

    @PostConstruct
    fun init() {
        secret = Base64.getEncoder().encodeToString(tokenConfig.secret.toByteArray())
        expiration = tokenConfig.expiration.toLong()
    }

    fun createToken(username: String, roles: List<UserRole>): String {
        logger.info { "create token username: $username, roles: $roles" }
        val now = Date()

        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(Date(now.time + expiration))
            .claim("roles", roles)
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val parseSignedClaims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.toByteArray())).build().parseSignedClaims(token)

            if (parseSignedClaims.payload.expiration.before(Date())) {
                throw ExpiredJwtException(null, null, "Token has expired")
            }

            logger.info { "Token is valid, user: ${parseSignedClaims.payload.subject}" }
            true
        } catch (expiredToken: ExpiredJwtException) {
            throw expiredToken
        } catch (otherException: Exception) {
            val errorMessage = when (otherException) {
                is SecurityException, is MalformedJwtException -> "Invalid token"
                else -> "Invalid token"
            }

            false
        }
    }
}
