package com.agent.agentemployment.config

import com.agent.agentemployment.domain.model.AgentUser
import com.agent.agentemployment.domain.persistence.AgentUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val agentUserRepository: AgentUserRepository,
    private val adminConfig: AdminConfig,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val adminUser = AgentUser().apply {
            username = adminConfig.username
            password = bCryptPasswordEncoder.encode(adminConfig.password)
            name = adminConfig.name
            isAdmin = true
        }
        agentUserRepository.save(adminUser)
    }
}
