package com.agent.agentemployment.dto.utils

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ResponseDTO (
    val code: Int,
    val message: String,
    val data: Any?,
    val responseTime: String = LocalDateTime.now().toString()
) {
    companion object {
        fun success(code: Int = HttpStatus.OK.value(), message: String = HttpStatus.OK.reasonPhrase, data: Any?): ResponseDTO =
            ResponseDTO(code, message, data)

        fun error(code: Int = HttpStatus.BAD_REQUEST.value(), message: String = HttpStatus.BAD_REQUEST.reasonPhrase, data: Any? = null): ResponseDTO =
            ResponseDTO(code, message, data)
    }
}