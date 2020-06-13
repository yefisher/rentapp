package com.efisher.flatrent.controller;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;
import com.efisher.flatrent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final MongoTemplate mongoTemplate;

    public UserController(UserService userService, MongoTemplate mongoTemplate) {
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<User> registerNewUserAccount(@RequestBody UserDTO userDTO) {

        LOGGER.info("Performing new user registration with info {}", userDTO);
        User user = null;
        try {
            user = userService.registerNewUserAccount(userDTO);
        } catch (RuntimeException e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().build();
        }

        LOGGER.info("New user {} has been registered", userDTO);

        assert user != null;
        return ResponseEntity.ok(user);
    }
}
