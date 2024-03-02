package com.agent.agentemployment.domain.persistence.utils

import com.agent.agentemployment.domain.persistence.AgentUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RepositoryUtilsKtTest @Autowired constructor(
    private val userRepository: AgentUserRepository,
) {

    private val errorNumber = -999L

    @Test
    fun findByIdOrThrow() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            userRepository.findByIdOrThrow(errorNumber, "User not found")
        }.message

        assertThat(errorMessage).isEqualTo("User not found")
    }

    @Test
    fun testFindByIdOrThrow() {
        val errorMessage = assertThrows<IllegalStateException> {
            userRepository.findByIdOrThrow(errorNumber, IllegalStateException("User not found"))
        }.message

        assertThat(errorMessage).isEqualTo("User not found")
    }
}
