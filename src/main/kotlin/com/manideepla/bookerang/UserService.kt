package com.manideepla.bookerang

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun signupUser(user: User): Mono<User> {
        return userRepository.save(user)
    }
}