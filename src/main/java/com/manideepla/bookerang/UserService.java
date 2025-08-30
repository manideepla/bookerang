package com.manideepla.bookerang;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    String login(String username, String password) throws LoginException {

        Optional<User> result = userRepository.findById(username);

        if (result.isEmpty()) throw new LoginException("Username not found");

        User user = result.get();

        if (!checkPassword(user.password, password)) {
            throw new LoginException("Password not correct");
        }

        return result.get().username; //TODO change this to a JWT
    }


    boolean checkPassword(String fetchedHash, String givenPassword) {
        return BCrypt.checkpw(givenPassword, fetchedHash);
    }
}
