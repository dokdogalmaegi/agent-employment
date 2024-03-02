package com.agent.agentemployment.security.session

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@SpringBootTest
class AgentUserDetailsServiceImplTest @Autowired constructor(
    private val agentUserDetailsServiceImpl: UserDetailsService
) {

    @Test
    fun `should get admin user principal`() {
        val adminUserPrincipal: AgentPrincipal = agentUserDetailsServiceImpl.loadUserByUsername("admin") as AgentPrincipal

        assertThat(adminUserPrincipal.username).isEqualTo("admin")
    }

    @Test
    fun `should throw exception when user not found`() {
        assertThrows<UsernameNotFoundException> {
            agentUserDetailsServiceImpl.loadUserByUsername("not-found-user")
        }
    }
}
