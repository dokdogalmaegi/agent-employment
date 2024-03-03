package com.agent.agentemployment.dto.security

import com.agent.agentemployment.dto.security.UserSignInDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class UserSignInDTOTest {

    @Test
    fun `should get properties`() {
        val userSignInDTO: UserSignInDTO = UserSignInDTO(
            "test1",
            "test2"
        )

        assertAll(
            { assertThat(userSignInDTO.username).isEqualTo("test1") },
            { assertThat(userSignInDTO.password).isEqualTo("test2") }
        )
    }
}