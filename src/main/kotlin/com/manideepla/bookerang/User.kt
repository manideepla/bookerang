package com.manideepla.bookerang

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(val username: String, @Column("first_name") val firstName: String, @Column("last_name") val lastName: String)
