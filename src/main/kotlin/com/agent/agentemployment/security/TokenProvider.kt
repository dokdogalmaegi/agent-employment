package com.agent.agentemployment.security

import com.agent.agentemployment.config.TokenConfig
import org.springframework.stereotype.Component

@Component
class TokenProvider(
    private val tokenConfig: TokenConfig
)
