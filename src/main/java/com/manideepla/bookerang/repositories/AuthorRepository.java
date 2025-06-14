package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.Author;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AuthorRepository extends ReactiveCrudRepository<Author, UUID> {

    // Custom query for saving or getting author
    @Query(value = """
    INSERT INTO authors (name) VALUES (:authorName)
    ON CONFLICT (name) DO UPDATE
    SET name = authors.name
    RETURNING id, name
    """)
    Mono<Author> saveOrGetAuthor(String authorName);
}
