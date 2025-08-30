package com.manideepla.bookerang;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class UserController {


    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    void signup() {

    }


    @PostMapping("/login")
    ResponseEntity<MsgResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("username: {}", loginRequest.username());
        String token = null;
        try {
            token = userService.login(loginRequest.username(), loginRequest.password());
        } catch (LoginException e) {
            log.error("Error logging in user: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new MsgResponse(e.getMessage()));
        }

        return ResponseEntity.ok().body(new MsgResponse(token));
    }
}
