package com.manideepla.bookerang.handlers;

import com.manideepla.bookerang.models.AddBookRequest;
import com.manideepla.bookerang.models.Author;
import com.manideepla.bookerang.models.Book;
import com.manideepla.bookerang.models.Copy;
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

    public Mono<UUID> addBook(AddBookRequest book, String username) {
        String authorName = book.author();
        Mono<Author> author = authorRepository.save(new Author(null, authorName));
        return author.map(a -> new Book(book.title(), a.id(), null))
                .flatMap(b -> bookRepository.save(b))
                .map(b -> new Copy(null, b.id(), username))
                .flatMap(c -> copyRepository.save(c))
                .map(Copy::id);
    }
}
