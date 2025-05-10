package com.manideepla.bookerang.repositories;

import com.manideepla.bookerang.models.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String username);


    // Custom query for geospatial search
    @Query(value = """
    WITH excluded_user_location AS (
        SELECT location
        FROM users
        WHERE username = :excludeUsername
    )
    SELECT *
    FROM users
    WHERE username != :excludeUsername
      AND ST_DWithin(
            users.location,
            (SELECT location FROM excluded_user_location),
            :distance
          )
    """)
    Flux<User> findUsersWithinDistance(@Param("distance") double distance,
                                       @Param("excludeUsername") String excludeUsername);
}
