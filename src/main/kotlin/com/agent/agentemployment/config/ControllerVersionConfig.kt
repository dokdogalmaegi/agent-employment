package com.agent.agentemployment.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.HandlerTypePredicate
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ControllerVersionConfig : WebMvcConfigurer {

    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.addPathPrefix(
            "/api/v1",
            HandlerTypePredicate.forBasePackage("com.agent.agentemployment.controller.v1")
        )
    }
}
