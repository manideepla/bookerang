package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface BookRepository extends ReactiveCrudRepository<Book, UUID> {
}
