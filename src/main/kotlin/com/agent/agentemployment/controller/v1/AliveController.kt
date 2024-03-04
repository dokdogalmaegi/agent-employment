package com.agent.agentemployment.controller.v1

import com.agent.agentemployment.dto.utils.ResponseDTO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/alive", produces = [MediaType.APPLICATION_JSON_VALUE])
class AliveController {
    @GetMapping
    fun alive(): ResponseDTO = ResponseDTO.success(data = "I'm alive!")
}
