package com.efisher.flatrent.controller;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    @RequestMapping
    public ResponseEntity<User> performSignUp(@RequestBody @Validated UserDTO userDTO) {

        LOGGER.debug("Performing new user registration with info {}", userDTO);

        //todo: implement registration process
        return null;
    }
}
