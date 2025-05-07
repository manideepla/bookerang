package com.manideepla.bookerang.controllers;


import com.manideepla.bookerang.handlers.BookHandler;
import com.manideepla.bookerang.models.AddBookRequest;
import com.manideepla.bookerang.models.GetBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookHandler bookHandler;

    @PostMapping("/add")
    Mono<ResponseEntity<UUID>> addBook(@RequestBody AddBookRequest book) {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> context.getAuthentication().getName())
                .flatMap(username -> bookHandler.addBook(book, username)
                .map(ResponseEntity::ok));
    }


    @GetMapping("/user")
    Mono<ResponseEntity<GetBooksResponse>> getBooks() {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> securityContext.getAuthentication().getName())
                .flatMap(username -> bookHandler.getBooks(username))
                .map(ResponseEntity::ok);

    }
}
