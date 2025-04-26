package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface AuthorRepository extends ReactiveCrudRepository<Author, UUID> {
}
