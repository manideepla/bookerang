package com.manideepla.bookerang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/{username}")
    public Mono<User> findUserByUsername(@PathVariable("username") String username) {
        return userService.findUser(username);
    }


    @PostMapping("/signup")
    public Mono<User> signupUser(@RequestBody User user) {
        return userService.signupUser(user);
    }
}
