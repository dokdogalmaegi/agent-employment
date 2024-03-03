package com.agent.agentemployment.controller.v1

import com.agent.agentemployment.domain.service.UserService
import com.agent.agentemployment.dto.UserSignInDTO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth", produces = [MediaType.APPLICATION_JSON_VALUE])
class AuthController(
    private val userService: UserService
) {

    @PostMapping
    fun signIn(@RequestBody userSignInDTO: UserSignInDTO): String {
        val (username: String, password: String) = userSignInDTO
        val accessToken = userService.createToken(username, password)

        return accessToken
    }
}
