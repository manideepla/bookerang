package com.manideepla.bookerang

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun signupUser(user: User): Result<User> {
      return try {
            val u = userRepository.save(user)
            Result.success(u)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    fun loginUser(user: User): Result<User?> {
        return try {
            val u = userRepository.findByUsername(user.username)
            Result.success(u)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}