package com.manideepla.bookerang;


import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserMinion {

    Mono<User> rehashedUser(String hashed, User u) {
        User rehashedUser = new User(u.username(), hashed, u.firstName(), u.lastName());
        return Mono.just(rehashedUser);
    }
}
