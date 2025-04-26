package com.manideepla.bookerang.handlers;

import com.manideepla.bookerang.models.AddBookRequest;
import com.manideepla.bookerang.models.Author;
import com.manideepla.bookerang.models.Book;
import com.manideepla.bookerang.repositories.AuthorRepository;
import com.manideepla.bookerang.repositories.BookRepository;
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

    public Mono<UUID> addBook(AddBookRequest book) {
        String authorName = book.author();
        Mono<Author> author = authorRepository.save(new Author(null, authorName));
        //TODO also write to copies table
        return author.map(a -> new Book(book.title(), a.id(), null))
                .flatMap(b -> bookRepository.save(b))
                .map(Book::id);
    }
}
