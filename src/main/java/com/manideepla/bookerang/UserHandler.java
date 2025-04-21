package com.manideepla.bookerang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMinion userMinion;

    Mono<String> saveUser(User user) {

        return Mono.just(user)
                .map(u -> passwordEncoder.encode(user.password()))
                .flatMap(hashed -> userMinion.rehashedUser(hashed, user))
                .flatMap(hashedUser -> userRepository.save(hashedUser)).map(User::username);
    }


    Mono<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }
}
