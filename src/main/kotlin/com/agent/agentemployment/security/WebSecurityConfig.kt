package com.agent.agentemployment.security

import com.agent.agentemployment.security.session.AgentAuthenticationFilter
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
class WebSecurityConfig(private val tokenProvider: TokenProvider) {
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it
                .ignoring()
                .requestMatchers("/h2-console/**")
        }
    }

    @Bean
    @Profile("dev")
    fun devFilter(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .cors { it.disable() }
            .csrf { it.disable() }
            .sessionManagement {
                it
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.addFilterAt(
                AgentAuthenticationFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            ).authorizeHttpRequests {
                it.requestMatchers(*PERMIT_ALL_URLS).permitAll()

                it.requestMatchers("/api/v1/**").authenticated()
            }

        return httpSecurity.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    companion object {
        val PERMIT_ALL_URLS = arrayOf(
            "/api/v1/users",
            "/api/v1/auth",
            "/api/v1/alive",
        )
    }
}
