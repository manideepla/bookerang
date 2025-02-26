package com.manideepla.bookerang

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/signup")
    fun userSignup(@RequestBody user: User): Mono<User> {
        return userService.signupUser(user)
    }

}