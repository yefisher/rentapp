package com.efisher.flatrent.controller;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;
import com.efisher.flatrent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration")
    public ResponseEntity<User> registerNewUserAccount(@RequestBody UserDTO userDTO) {

        LOGGER.debug("Performing new user registration with info {}", userDTO);

        final User user = userService.registerNewUserAccount(userDTO);
        //todo: implement registration process
        return ResponseEntity.ok(user);
    }
}
