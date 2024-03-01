package com.agent.agentemployment.security.session

import com.agent.agentemployment.domain.model.AgentUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AgentPrincipalTest {

    private val agentAdminUser: AgentUser = AgentUser()
    private var agentAdminPrincipal: AgentPrincipal? = null

    @BeforeEach
    fun setUp() {
        agentAdminUser.name = "admin"
        agentAdminUser.username = "admin"
        agentAdminUser.password = "admin"
        agentAdminUser.isAdmin = true

        agentAdminPrincipal = AgentPrincipal(agentAdminUser)
    }

    @Test
    fun getAuthorities() {
        assertThat(agentAdminPrincipal!!.authorities).contains(SimpleGrantedAuthority("ROLE_ADMIN"))
    }

    @Test
    fun getAuthoritiesNotContainsUser() {
        val agentUser: AgentUser = AgentUser()
        agentUser.name = "user"
        agentUser.username = "user"
        agentUser.password = "user"
        agentUser.isAdmin = false

        val agentUserPrincipal: AgentPrincipal = AgentPrincipal(agentUser)
        assertAll(
            { assertThat(agentUserPrincipal.authorities).contains(SimpleGrantedAuthority("ROLE_USER")) },
            { assertThat(agentUserPrincipal.authorities).doesNotContain(SimpleGrantedAuthority("ROLE_ADMIN")) }
        )
    }

    @Test
    fun getPassword() {
        assertThat(agentAdminPrincipal!!.password).isEqualTo("admin")
    }

    @Test
    fun getUsername() {
        assertThat(agentAdminPrincipal!!.username).isEqualTo("admin")
    }

    @Test
    fun isAccountNonExpired() {
        assertThat(agentAdminPrincipal!!.isAccountNonExpired).isTrue()
    }

    @Test
    fun isAccountNonLocked() {
        assertThat(agentAdminPrincipal!!.isAccountNonLocked).isTrue()
    }

    @Test
    fun isCredentialsNonExpired() {
        assertThat(agentAdminPrincipal!!.isCredentialsNonExpired).isTrue()
    }

    @Test
    fun isEnabled() {
        assertThat(agentAdminPrincipal!!.isEnabled).isTrue()
    }
}
