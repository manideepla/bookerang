package com.manideepla.bookerang;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @PostMapping("/signup")
    void signupUser(@RequestBody User user) {
        System.out.println(user);
    }

}
