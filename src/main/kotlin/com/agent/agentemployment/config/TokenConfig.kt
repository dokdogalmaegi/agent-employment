package com.agent.agentemployment.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "token")
class TokenConfig {
    lateinit var secret: String
    lateinit var expiration: String
}
