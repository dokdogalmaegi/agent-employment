package com.agent.agentemployment.domain.service

import com.agent.agentemployment.dto.UserSignUpDTO

interface UserService {

    fun createUser(userSignUpDTO: UserSignUpDTO)
}
