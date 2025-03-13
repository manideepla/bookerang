package com.manideepla.bookerang

import jakarta.servlet.http.HttpSession
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/user")
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var httpSession: HttpSession

    @GetMapping("/signup")
    fun showSignupForm(model: Model): String {
        model.addAttribute("user", User())
        return "signup"
    }

    @PostMapping("/signup")
    fun userSignup(@ModelAttribute user: User): String {
        val u = userService.signupUser(user)

        return if (u.isSuccess) {
            httpSession.setAttribute("username", u.getOrNull()?.username)
            "redirect:/user/home?username=${u.getOrNull()?.username}"
        } else {
            "redirect:/user/failure?message=Signup+failed+for+user+${user.username}"
        }
    }

    @GetMapping("/login")
    fun showLoginForm(model: Model): String {
        model.addAttribute("user", User())
        return "login"
    }

    @PostMapping("/login")
    fun userLogin(@ModelAttribute user: User): String {
        logger.info("trying to login user: $user")
        val u = userService.loginUser(user)

        return if (u.isSuccess) {
            httpSession.setAttribute("username", u.getOrNull()?.username)
            "redirect:/user/home?username=${u.getOrNull()?.username}"
        } else {
            val msg = "Login failed for user ${user.username}"
            logger.error("logging in failed for user: ${u.exceptionOrNull()?.message}")
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