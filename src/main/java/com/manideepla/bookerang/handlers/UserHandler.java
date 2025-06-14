package com.manideepla.bookerang.handlers;

import com.manideepla.bookerang.models.LoginRequest;
import com.manideepla.bookerang.models.NearbyUserItem;
import com.manideepla.bookerang.models.UserProfile;
import com.manideepla.bookerang.repositories.UserRepository;
import com.manideepla.bookerang.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZoneOffset;


@Service
public class UserHandler implements ReactiveUserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Mono<String> saveUser(User user) {

        return Mono.just(user)
                .map(u -> passwordEncoder.encode(user.password()))
                .flatMap(hashed -> rehashedUser(hashed, user))
                .flatMap(hashedUser -> userRepository.save(hashedUser)).map(User::username);
    }

    public Mono<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).cast(UserDetails.class);
    }

    public Mono<Boolean> loginUser(LoginRequest loginRequest) {
          return userRepository.findByUsername(loginRequest.username())
                  .map(user -> checkPassword(user.password(), loginRequest.password()));
    }

    boolean checkPassword(String encodedPassword, String password) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public Mono<Flux<NearbyUserItem>> findUsersNearBy(int radius) {
        Mono<String> username = ReactiveSecurityContextHolder.getContext().map(c -> c.getAuthentication().getName());

        return username.map(u -> userRepository.findUsersWithinDistance(radius, u));
    }

    public Mono<User> rehashedUser(String hashed, User u) {
        return Mono.just(u)
                .map(user -> new User(u.username(), hashed, u.firstName(), u.lastName(), u.createdAt()));
    }

    public UserProfile convertToProfile(User user) {
        return new UserProfile(user.firstName(), user.lastName(), user.username(), user.createdAt().withOffsetSameInstant(ZoneOffset.UTC));
    }
}
