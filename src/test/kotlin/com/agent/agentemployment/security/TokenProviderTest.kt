package com.agent.agentemployment.security

import com.agent.agentemployment.domain.enums.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TokenProviderTest @Autowired constructor(
    private val tokenProvider: TokenProvider
) {

    @Test
    fun createToken() {
        val token = tokenProvider.createToken("username", listOf(UserRole.USER))
        assertThat(token).isNotEmpty()
    }
}
