package com.manideepla.bookerang.controllers;


import com.manideepla.bookerang.handlers.BookHandler;
import com.manideepla.bookerang.models.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookHandler bookHandler;

    @PostMapping("/add")
    Mono<ResponseEntity<UUID>> addBook(@RequestBody AddBookRequest book) {
        return bookHandler.addBook(book).map(ResponseEntity::ok);
    }
}
