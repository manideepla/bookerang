package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.UserCopy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CopyRepository extends ReactiveCrudRepository<UserCopy, UUID> {
    Flux<UserCopy> findAllByOwner(String owner);
}
