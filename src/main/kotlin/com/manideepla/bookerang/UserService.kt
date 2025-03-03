package com.manideepla.bookerang

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun signupUser(user: User): User {
        return userRepository.save(user)
    }
}