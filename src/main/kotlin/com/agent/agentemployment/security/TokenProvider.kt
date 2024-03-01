package com.agent.agentemployment.security

import com.agent.agentemployment.config.TokenConfig
import com.agent.agentemployment.domain.enums.UserRole
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenProvider(
    private val tokenConfig: TokenConfig
) {

    private var secret: String = ""
    private var expiration: Long = 0L

    @PostConstruct
    private fun init() {
        secret = Base64.getEncoder().encodeToString(tokenConfig.secret.toByteArray())
        expiration = tokenConfig.expiration.toLong()
    }

    fun createToken(username: String, roles: List<UserRole>): String {
        val now = Date()

        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(Date(now.time + expiration))
            .claim("roles", roles)
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .compact()
    }
}
