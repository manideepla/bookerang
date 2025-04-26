package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.UserCopy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CopyRepository extends ReactiveCrudRepository<UserCopy, UUID> {
}
