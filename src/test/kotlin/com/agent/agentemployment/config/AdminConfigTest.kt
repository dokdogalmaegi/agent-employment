package com.agent.agentemployment.config

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AdminConfigTest @Autowired constructor(private val adminConfig: AdminConfig) {

    @Test
    fun `should not empty username`() {
        assertThat(adminConfig.username).isNotEmpty()
    }

    @Test
    fun `should not empty password`() {
        assertThat(adminConfig.password).isNotEmpty()
    }

    @Test
    fun `should not empty name`() {
        assertThat(adminConfig.name).isNotEmpty()
    }
}
