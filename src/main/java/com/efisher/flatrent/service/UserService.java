package com.efisher.flatrent.service;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;
import com.efisher.flatrent.error.UserAlreadyExistsException;
import com.efisher.flatrent.repository.UserRepository;
import com.efisher.flatrent.util.UserDTOConverter;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final MongoOperations mongoOperations;

    private final PasswordEncoder passwordEncoder;

    private final SequenceGeneratorService sequenceGeneratorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, MongoOperations mongoOperations, PasswordEncoder passwordEncoder,
                       SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.mongoOperations = mongoOperations;
        this.passwordEncoder = passwordEncoder;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public User registerNewUserAccount(final UserDTO userDto) {
        LOGGER.info("Performing creating new record to database...");

        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Failed to create document. There is an account with given email address: '%s'",
                    userDto.getEmail()));
        } else if (usernameExists(userDto.getUsername())) {
            throw new UserAlreadyExistsException(String.format("Failed to create document. There is an account with given username: '%s'",
                    userDto.getUsername()));
        }

        User user = UserDTOConverter.fromUserDtoToUserToRegister(userDto);
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getUserList() {
        return mongoOperations.findAll(User.class);
    }

    private Boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private Boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
