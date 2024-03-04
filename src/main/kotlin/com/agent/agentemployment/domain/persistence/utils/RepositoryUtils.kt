package com.agent.agentemployment.domain.persistence.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(ex: Exception?): Nothing {
    throw ex ?: IllegalArgumentException()
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, ex: Exception? = null): T {
    return this.findByIdOrNull(id) ?: fail(ex)
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String? = null): T {
    return this.findByIdOrThrow(id, IllegalArgumentException(message))
}
