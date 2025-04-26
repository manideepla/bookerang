package com.manideepla.bookerang.handlers;

import com.manideepla.bookerang.models.AddBookRequest;
import com.manideepla.bookerang.models.Author;
import com.manideepla.bookerang.models.Book;
import com.manideepla.bookerang.models.UserCopy;
import com.manideepla.bookerang.repositories.AuthorRepository;
import com.manideepla.bookerang.repositories.BookRepository;
import com.manideepla.bookerang.repositories.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class BookHandler {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CopyRepository copyRepository;

    public Mono<UUID> addBook(AddBookRequest addBookRequest, String username) {
        return  Mono.just(addBookRequest.author())
                .flatMap( authorName -> authorRepository.save(new Author(null, authorName)))
                .map(author -> new Book(addBookRequest.title(), author.id(), null))
                .flatMap(book -> bookRepository.save(book))
                .map(savedBook -> new UserCopy(null, savedBook.id(), username))
                .flatMap(userCopy -> copyRepository.save(userCopy))
                .map(UserCopy::id);
    }
}
