package com.manideepla.bookerang.minions;


import com.manideepla.bookerang.models.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserMinion {

    public Mono<User> rehashedUser(String hashed, User u) {
        User rehashedUser = new User(u.username(), hashed, u.firstName(), u.lastName());
        return Mono.just(rehashedUser);
    }
}
