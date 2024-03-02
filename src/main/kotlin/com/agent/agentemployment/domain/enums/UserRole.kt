package com.agent.agentemployment.domain.enums
import lombok.AllArgsConstructor
import lombok.Getter

const val USER_ROLE = "USER"
const val ADMIN_ROLE = "ADMIN"

@AllArgsConstructor
@Getter
enum class UserRole(val value: String) {
    USER(USER_ROLE),
    ADMIN(ADMIN_ROLE)
}
