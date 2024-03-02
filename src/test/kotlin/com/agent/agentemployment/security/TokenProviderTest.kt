package com.agent.agentemployment.security

import com.agent.agentemployment.config.TokenConfig
import com.agent.agentemployment.domain.enums.UserRole
import io.jsonwebtoken.ExpiredJwtException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

val logger = mu.KotlinLogging.logger {}

@SpringBootTest
class TokenProviderTest @Autowired constructor(
    private val tokenProvider: TokenProvider,
    private val tokenConfig: TokenConfig
) {

    @Test
    fun `should create access token`() {
        val token = tokenProvider.createToken("username", listOf(UserRole.USER))
        assertThat(token).isNotEmpty()
    }

    @Test
    fun `should validate token`() {
        val token = tokenProvider.createToken("username", listOf(UserRole.USER))
        val isValid = tokenProvider.validateToken(token)
        assertThat(isValid).isTrue()
    }

    @Test
    fun `should not validate token when expired`() {
        tokenConfig.secret = "64yA7Lap67mE67CA7Yag7YGw7J6F64uI64uk"
        tokenConfig.expiration = "0"

        val expiredTokenProvider: TokenProvider = TokenProvider(tokenConfig)
        expiredTokenProvider.init()
        val token = expiredTokenProvider.createToken("username", listOf(UserRole.USER))

        assertThrows<ExpiredJwtException> {
            expiredTokenProvider.validateToken(token)
        }
    }

    @Test
    fun `should not validate token when invalid`() {
        val invalidToken = "invalid-token"

        val isValid = tokenProvider.validateToken(invalidToken)

        assertThat(isValid).isFalse()
    }
}
