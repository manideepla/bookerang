package com.manideepla.bookerang.minions;


import com.manideepla.bookerang.models.User;
import com.manideepla.bookerang.models.UserProfile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.time.ZoneOffset;

@Component
public class UserMinion {

    public Mono<User> rehashedUser(String hashed, User u) {
        return Mono.just(u)
                .map(user -> new User(u.username(), hashed, u.firstName(), u.lastName(), u.createdAt()));
    }

    public UserProfile convertToProfile(User user) {
        return new UserProfile(user.firstName(), user.lastName(), user.username(), user.createdAt().withOffsetSameInstant(ZoneOffset.UTC));
    }
}
