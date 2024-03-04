package com.agent.agentemployment.dto.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.http.HttpStatus

class ResponseDTOTest {

    private val defaultData = "test"

    @Test
    fun `should get 200 code from default code, success function`() {
        val responseDTO: ResponseDTO = ResponseDTO.success(data = defaultData)
        assertThat(responseDTO.code).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun `should get 200 message from default message, success function`() {
        val responseDTO: ResponseDTO = ResponseDTO.success(data = defaultData)
        assertThat(responseDTO.message).isEqualTo(HttpStatus.OK.reasonPhrase)
    }

    @Test
    fun `should get data, success function`() {
        val successDTO: ResponseDTO = ResponseDTO.success(data = defaultData)
        val failDTO: ResponseDTO = ResponseDTO.fail(data = defaultData)
        assertAll(
            { assertThat(successDTO.data).isEqualTo(defaultData) },
            { assertThat(failDTO.data).isEqualTo(defaultData) }
        )
    }

    @Test
    fun `should get 400 code from default code, fail function`() {
        val responseDTO: ResponseDTO = ResponseDTO.fail(data = defaultData)
        assertThat(responseDTO.code).isEqualTo(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should get 400 message from default message, fail function`() {
        val responseDTO: ResponseDTO = ResponseDTO.fail(data = defaultData)
        assertThat(responseDTO.message).isEqualTo(HttpStatus.BAD_REQUEST.reasonPhrase)
    }
}