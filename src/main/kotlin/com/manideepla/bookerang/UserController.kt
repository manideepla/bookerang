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
        return u.let {
            "redirect:/user/signupSuccess?username=${u.username}"
        }
    }

    @GetMapping("/signupSuccess")
    fun showSignupSuccess(@RequestParam username: String, model: Model): String {
        model.addAttribute("username", username)
        return "signupSuccess"
    }
}