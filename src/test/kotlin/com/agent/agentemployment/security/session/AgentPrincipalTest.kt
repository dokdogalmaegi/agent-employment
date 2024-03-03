package com.agent.agentemployment.security.session

import com.agent.agentemployment.config.AdminConfig
import com.agent.agentemployment.domain.enums.UserRole
import com.agent.agentemployment.domain.model.AgentUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.authority.SimpleGrantedAuthority

@SpringBootTest
class AgentPrincipalTest @Autowired constructor(
    private val adminConfig: AdminConfig
) {

    private val agentAdminUser = AgentUser()
    private var agentAdminPrincipal: AgentPrincipal? = null

    @BeforeEach
    fun setUp() {
        agentAdminUser.name = adminConfig.name
        agentAdminUser.username = adminConfig.username
        agentAdminUser.password = adminConfig.password
        agentAdminUser.isAdmin = true

        agentAdminPrincipal = AgentPrincipal(agentAdminUser)
    }

    @Test
    fun getAuthorities() {
        assertThat(agentAdminPrincipal!!.authorities).contains(SimpleGrantedAuthority("ROLE_${UserRole.ADMIN}"))
    }

    @Test
    fun getAuthoritiesNotContainsUser() {
        val agentUser = AgentUser()
        agentUser.name = "user"
        agentUser.username = "user"
        agentUser.password = "user"
        agentUser.isAdmin = false

        val agentUserPrincipal = AgentPrincipal(agentUser)
        assertAll(
            { assertThat(agentUserPrincipal.authorities).contains(SimpleGrantedAuthority("ROLE_${UserRole.USER}")) },
            { assertThat(agentUserPrincipal.authorities).doesNotContain(SimpleGrantedAuthority("ROLE_${UserRole.ADMIN}")) }
        )
    }

    @Test
    fun getPassword() {
        assertThat(agentAdminPrincipal!!.password).isEqualTo(adminConfig.password)
    }

    @Test
    fun getUsername() {
        assertThat(agentAdminPrincipal!!.username).isEqualTo(adminConfig.username)
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
