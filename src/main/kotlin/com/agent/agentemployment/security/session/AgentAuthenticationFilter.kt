package com.agent.agentemployment.security.session

import com.agent.agentemployment.security.TokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.GenericFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder

class AgentAuthenticationFilter(
    private val tokenProvider: TokenProvider
) : GenericFilter() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token: String? = (request as HttpServletRequest)
            .getHeader("Authorization")
            ?.takeIf { it.startsWith("Bearer ") }
            ?.substring(7)

        token?.let {
            if (tokenProvider.validateToken(it)) {
                SecurityContextHolder.getContext().authentication = tokenProvider.getAuthentication(it)
            }
        }

        chain.doFilter(request, response)
    }
}
