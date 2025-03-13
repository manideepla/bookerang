package com.manideepla.bookerang

import jakarta.servlet.http.HttpSession
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {

    private val logger = LoggerFactory.getLogger(HomeController::class.java)

    @Autowired
    lateinit var httpSession: HttpSession

    @GetMapping("/")
    fun showHome(): String {
        val username = httpSession.getAttribute("username")

        logger.info("user: $username")

        return if (username != null) {
            "redirect:/user/home?username=${username}"
        } else {
            "redirect:/user/login"
        }
    }
}