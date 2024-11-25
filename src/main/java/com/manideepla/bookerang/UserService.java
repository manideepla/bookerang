package com.manideepla.bookerang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    // TODO   handle non-existence of username
    Mono<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }


    // TODO handle uniqueness of username
    Mono<User> signupUser(User user) {
        return userRepository.save(user);
    }

}
