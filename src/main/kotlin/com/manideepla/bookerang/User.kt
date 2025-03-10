package com.manideepla.bookerang

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id val id: Long? = null,
    val username: String = "",
    @Column("first_name") val firstName: String = "",
    @Column("last_name") val lastName: String = "",
    val password: String = ""
)
