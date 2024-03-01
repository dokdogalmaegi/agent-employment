package com.agent.agentemployment.domain.model

import com.agent.agentemployment.domain.enums.UserRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor

@Entity
@AllArgsConstructor
class AgentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0L

    var name: String = ""

    @Column(unique = true)
    var username: String = ""

    var password: String = ""

    var isAdmin: Boolean = false

    @delegate:Transient
    val roles: Set<UserRole> by lazy {
        buildSet {
            add(UserRole.USER)
            if (isAdmin) add(UserRole.ADMIN)
        }
    }
}
