package com.manideepla.bookerang

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : CrudRepository<User, String> {
    fun findByUsername(username: String): User
}