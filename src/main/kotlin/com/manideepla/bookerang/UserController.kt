package com.manideepla.bookerang

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/signup")
    fun showSignupForm(model: Model): String {
        model.addAttribute("user", User())
        return "signup"
    }

    @PostMapping("/signup")
    fun userSignup(@ModelAttribute user: User): String {
        val u = userService.signupUser(user)

        return if (u.isSuccess) {
            "redirect:/user/home?username=${u.getOrNull()?.username}"
        } else {
            val msg = "Signup failed for user ${user.username}"
            "redirect:/user/failure?message=$msg"
        }
    }

    @GetMapping("/login")
    fun showLoginForm(model: Model): String {
        model.addAttribute("user", User())
        return "login"
    }

    @PostMapping("/login")
    fun userLogin(@ModelAttribute user: User): String {
        val u = userService.loginUser(user)

        return if (u.isSuccess) {
            "redirect:/user/home?username=${u.getOrNull()?.username}"
        } else {
            val msg = "Login failed for user ${user.username}"
            "redirect:/user/failure?message=$msg"
        }
    }


    @GetMapping("/home")
    fun showUserHome(@RequestParam username: String, model: Model): String {
        model.addAttribute("username", username)
        return "home"
    }

    @GetMapping("/failure")
    fun showFailure(@RequestParam message: String, model: Model): String {
        model.addAttribute("message", message)
        return "error"
    }

}