package com.agent.agentemployment.security.session

import com.agent.agentemployment.domain.persistence.AgentUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AgentUserDetailsServiceImpl(
    private val agentUserRepository: AgentUserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        agentUserRepository.findByUsername(username)?.let {
            AgentPrincipal(it)
        } ?: throw UsernameNotFoundException(username)
}
