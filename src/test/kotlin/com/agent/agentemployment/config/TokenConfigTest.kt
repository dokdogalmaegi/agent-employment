package com.agent.agentemployment.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TokenConfigTest @Autowired constructor(
    private val tokenConfig: TokenConfig
) {

    @Test
    fun `should not empty secret`() {
        assertThat(tokenConfig.secret).isNotEmpty()
    }

    @Test
    fun `should not empty expiration`() {
        assertThat(tokenConfig.expiration).isNotEmpty()
    }
}
