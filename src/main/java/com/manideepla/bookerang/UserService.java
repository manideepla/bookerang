package com.manideepla.bookerang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    Mono<String> saveUser(User user) {

        String hashed = passwordEncoder.encode(user.password());

        User hashedUser = new User(
                user.username(),
                hashed,
                user.firstName(),
                user.lastName()
        );

        return userRepository.save(hashedUser).map(User::username);
    }

    Mono<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }
}
