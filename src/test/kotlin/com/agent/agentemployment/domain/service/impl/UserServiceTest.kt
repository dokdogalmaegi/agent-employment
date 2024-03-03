package com.agent.agentemployment.domain.service.impl

import com.agent.agentemployment.config.AdminConfig
import com.agent.agentemployment.domain.persistence.AgentUserRepository
import com.agent.agentemployment.domain.service.UserService
import com.agent.agentemployment.dto.UserSignUpDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: AgentUserRepository,
    private val adminConfig: AdminConfig
) {

    @Test
    @Transactional
    fun `should create user`() {
        val userSignUpDTO: UserSignUpDTO = UserSignUpDTO(
            "test1",
            "test2",
            "test3"
        )

        assertAll(
            { assertDoesNotThrow { userService.createUser(userSignUpDTO) } },
            { assertThat(userRepository.findByUsername(userSignUpDTO.username)).isNotNull }
        )
    }

    @Test
    fun `should throw exception when signup a user with the same username`() {
        val userSignUpDTO: UserSignUpDTO = UserSignUpDTO(
            "test1",
            adminConfig.username,
            "test3"
        )

        val errorMessage = assertThrows<IllegalArgumentException> {
            userService.createUser(userSignUpDTO)
        }.message
        assertThat(errorMessage).isEqualTo("User already exists")
    }

    @Test
    fun `should get access token with the valid user`() {
        val accessToken = assertDoesNotThrow {
            userService.createToken(adminConfig.username, adminConfig.password)
        }

        assertThat(accessToken).isNotEmpty()
    }
}