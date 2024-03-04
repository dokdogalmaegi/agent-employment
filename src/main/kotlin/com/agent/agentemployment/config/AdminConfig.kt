package com.agent.agentemployment.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "admin-user")
class AdminConfig {
    lateinit var username: String
    lateinit var password: String
    lateinit var name: String
}
