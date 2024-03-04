package com.agent.agentemployment.dto.security

import com.agent.agentemployment.domain.model.AgentUser

data class UserSignUpDTO(
    val name: String,
    val username: String,
    val password: String,
)

fun UserSignUpDTO.toAgentUser(): AgentUser =
    AgentUser().apply {
        name = this@toAgentUser.name
        username = this@toAgentUser.username
        password = this@toAgentUser.password
    }
