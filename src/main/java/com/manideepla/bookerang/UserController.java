package com.manideepla.bookerang;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    Mono<ResponseEntity<MessageResponse>> userSignup(@RequestBody User user) {
        return userService.saveUser(user).map(u -> ResponseEntity.ok(new MessageResponse(u)));
    }


    @GetMapping("/{username}")
    Mono<ResponseEntity<User>> getUser(@PathVariable String username) {
        return userService.findUser(username).map(ResponseEntity::ok);
    }
}
