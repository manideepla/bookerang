package com.manideepla.bookerang.controllers;


import com.manideepla.bookerang.JwtUtil;
import com.manideepla.bookerang.handlers.UserHandler;
import com.manideepla.bookerang.models.LoginRequest;
import com.manideepla.bookerang.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserHandler userHandler;

    @Autowired
    ReactiveAuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    Mono<ResponseEntity<Map<String, String>>> userSignup(@RequestBody User user) {
        return userHandler.saveUser(user)
                .flatMap(u -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(), user.password())))
                .map(auth -> (UserDetails) auth.getPrincipal())
                .map(userDetails -> jwtUtil.generateToken(userDetails))
                .map(token -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    return ResponseEntity.ok()
                            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                            .body(response);
                });
    }

    @GetMapping("/{username}")
    Mono<ResponseEntity<User>> getUser(@PathVariable String username) {
        return userHandler.findUser(username).map(ResponseEntity::ok);
    }


    @PostMapping("/login")
    Mono<ResponseEntity<Map<String, String>>> login(@RequestBody LoginRequest loginRequest) {
        return userHandler.loginUser(loginRequest)
                .filter(x -> x)
                .map(ignore -> authenticationFLow(loginRequest.username(), loginRequest.password()))
                .flatMap(u -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())))
                .map(auth -> (UserDetails) auth.getPrincipal())
                .map(userDetails -> jwtUtil.generateToken(userDetails))
                .map(token -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    return ResponseEntity.ok()
                            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                            .body(response);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    Mono<ResponseEntity<Map<String, String>>> authenticationFLow(String username, String password) {
          return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .map(auth -> (UserDetails) auth.getPrincipal())
                .map(userDetails -> jwtUtil.generateToken(userDetails))
                .map(token -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    return ResponseEntity.ok()
                            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                            .body(response);
                });
    }

}
