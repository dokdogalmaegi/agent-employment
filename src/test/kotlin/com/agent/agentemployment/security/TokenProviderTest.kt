package com.agent.agentemployment.security

import com.agent.agentemployment.config.TokenConfig
import com.agent.agentemployment.domain.enums.UserRole
import com.agent.agentemployment.security.session.AgentPrincipal
import io.jsonwebtoken.ExpiredJwtException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService

val logger = mu.KotlinLogging.logger {}

@SpringBootTest
class TokenProviderTest @Autowired constructor(
    private val tokenProvider: TokenProvider,
    private val tokenConfig: TokenConfig,
    private val userDetailsService: UserDetailsService
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

        val expiredTokenProvider: TokenProvider = TokenProvider(tokenConfig, userDetailsService)
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

    @Test
    fun `should get Authentication from token`() {
        val token = tokenProvider.createToken("admin", listOf(UserRole.USER, UserRole.ADMIN))

        val authentication: Authentication = tokenProvider.getAuthentication(token)
        assertThat(authentication).isNotNull

        val agentPrincipal = authentication.principal as AgentPrincipal
        assertAll(
            { assertThat(agentPrincipal.username).isEqualTo("admin") },
            { assertThat(agentPrincipal.authorities).contains(SimpleGrantedAuthority("ROLE_${UserRole.USER}")) }
        )
    }
}
