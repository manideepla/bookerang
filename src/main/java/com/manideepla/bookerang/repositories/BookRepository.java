package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.Author;
import com.manideepla.bookerang.models.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BookRepository extends ReactiveCrudRepository<Book, UUID> {

    // Custom query for saving or getting book
    @Query(value = """
    INSERT INTO books (title, author) VALUES (:title, :authorId)
    ON CONFLICT (title, author) DO UPDATE
    SET title = EXCLUDED.title, author = EXCLUDED.author
    RETURNING id
    """)
    Mono<Book> saveOrGetBook(String title, UUID authorId);
}
