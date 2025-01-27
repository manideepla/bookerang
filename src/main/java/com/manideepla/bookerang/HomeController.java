package com.manideepla.bookerang;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
//     TODO this config is not working for some reason, figure it out
        return "home";
    }
}
