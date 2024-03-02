package com.agent.agentemployment.dto

import com.agent.agentemployment.domain.enums.UserRole
import com.agent.agentemployment.domain.model.AgentUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class UserSignUpDTOTest {

    private val userSignUpDTO: UserSignUpDTO = UserSignUpDTO(
        "test1",
        "test2",
        "test3"
    )

    @Test
    fun `should get properties`() {
        assertAll(
            { assertThat(userSignUpDTO.name).isEqualTo("test1") },
            { assertThat(userSignUpDTO.username).isEqualTo("test2") },
            { assertThat(userSignUpDTO.password).isEqualTo("test3") }
        )
    }

    @Test
    fun `should get UserSignUpDTO to AgentUser entity`() {
        val agentUser: AgentUser = userSignUpDTO.toAgentUser()
        assertAll(
            { assertThat(agentUser).isNotNull },
            { assertThat(agentUser.name).isEqualTo("test1") },
            { assertThat(agentUser.username).isEqualTo("test2") },
            { assertThat(agentUser.password).isEqualTo("test3") },
            { assertThat(agentUser.isAdmin).isFalse() },
            { assertThat(agentUser.roles).contains(UserRole.USER) },
        )
    }
}