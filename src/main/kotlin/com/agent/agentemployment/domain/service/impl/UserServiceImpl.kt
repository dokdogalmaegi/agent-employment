package com.agent.agentemployment.domain.service.impl

import com.agent.agentemployment.domain.persistence.AgentUserRepository
import com.agent.agentemployment.domain.service.UserService
import com.agent.agentemployment.dto.UserSignUpDTO
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: AgentUserRepository
) : UserService {
    override fun createUser(userSignUpDTO: UserSignUpDTO) {
        TODO("Not yet implemented")
    }
}
