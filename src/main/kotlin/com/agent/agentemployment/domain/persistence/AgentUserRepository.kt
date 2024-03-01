package com.agent.agentemployment.domain.persistence

import com.agent.agentemployment.domain.AgentUser
import org.springframework.data.jpa.repository.JpaRepository

interface AgentUserRepository : JpaRepository<AgentUser, Long> {
    fun findByUsername(username: String): AgentUser?
}
