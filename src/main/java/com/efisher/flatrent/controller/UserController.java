package com.efisher.flatrent.controller;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;
import com.efisher.flatrent.error.InfoUpdateException;
import com.efisher.flatrent.error.UserNotFoundException;
import com.efisher.flatrent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(value = "/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        LOGGER.info("Uploading user list...");
        List<User> users = userService.getUserList();
        if (CollectionUtils.isEmpty(users))
            LOGGER.info("No users were found in the system.");
        LOGGER.info("User list uploaded.");
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable final long id) {
        LOGGER.info(String.format("Uploading user with given id: %d.", id));
        User user;
        try {
            Optional<User> userOptional = userService.getUserById(id);
            user = userOptional.get();
        } catch (UserNotFoundException e) {
            LOGGER.info(String.format("No user with given id: %d was found.", id));
            return ResponseEntity.notFound().build();
        }
        LOGGER.info(String.format("User with id: %d has been uploaded.", id));
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody final UserDTO userDTO, @PathVariable final long id) {
        User user;
        try {
            user = userService.updateUser(userDTO, id);
        } catch (UserNotFoundException e) {
            LOGGER.info(String.format("No user with given id: %d", id));
            return ResponseEntity.notFound().build();
        } catch (InfoUpdateException e) {
            LOGGER.info(String.format("Exception occurred: such username already taken: %s", userDTO.getUsername()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> removeUser(@PathVariable final long id) {
        LOGGER.info(String.format("Removing user with id: %d.", id));
        User user;
        try {
            user = userService.removeUser(id);
        } catch (RuntimeException e) {
            LOGGER.info(String.format("Exception occurred while removing user with id: %d.", id));
            return ResponseEntity.badRequest().build();
        }
        LOGGER.info(String.format("Removed user with given id: %d.", id));
        return ResponseEntity.ok(user);
    }
}
