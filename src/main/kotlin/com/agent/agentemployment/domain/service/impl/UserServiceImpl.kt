package com.agent.agentemployment.domain.service.impl

import com.agent.agentemployment.domain.model.AgentUser
import com.agent.agentemployment.domain.persistence.AgentUserRepository
import com.agent.agentemployment.domain.service.UserService
import com.agent.agentemployment.dto.UserSignUpDTO
import com.agent.agentemployment.dto.toAgentUser
import com.agent.agentemployment.security.TokenProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: AgentUserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val tokenProvider: TokenProvider
) : UserService {
    override fun createUser(userSignUpDTO: UserSignUpDTO) {
        val existsUser: AgentUser? = userRepository.findByUsername(userSignUpDTO.username)
        require(existsUser == null) {
            "User already exists"
        }

        val cryptoUser = userSignUpDTO.copy(
            password = bCryptPasswordEncoder.encode(userSignUpDTO.password)
        )
        userRepository.save(cryptoUser.toAgentUser())
    }

    override fun createToken(username: String, password: String): String {
        val signInUser: AgentUser? = userRepository.findByUsername(username)

        val isValid = signInUser != null && bCryptPasswordEncoder.matches(password, signInUser.password)
        require(isValid) {
            "Invalid username or password."
        }

        return tokenProvider.createToken(signInUser!!.username, signInUser.roles.toList())
    }
}
