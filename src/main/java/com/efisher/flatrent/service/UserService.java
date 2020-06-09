package com.efisher.flatrent.service;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.domain.UserRole;
import com.efisher.flatrent.dto.UserDTO;
import com.efisher.flatrent.error.UserAlreadyExistsException;
import com.efisher.flatrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUserAccount(final UserDTO userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with given email address: " + userDto.getEmail());
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList(new UserRole("USER")));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }

    private Boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
