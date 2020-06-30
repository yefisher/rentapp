package com.efisher.flatrent.service;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;
import com.efisher.flatrent.error.InfoUpdateException;
import com.efisher.flatrent.error.UserAlreadyExistsException;
import com.efisher.flatrent.error.UserNotFoundException;
import com.efisher.flatrent.repository.UserRepository;
import com.efisher.flatrent.util.UserDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User saveUser(final UserDTO userDto) {
        LOGGER.info("Performing creating new record to database...");

        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Failed to create document. There is an account with given email address: '%s'",
                    userDto.getEmail()));
        } else if (usernameExists(userDto.getUsername())) {
            throw new UserAlreadyExistsException(String.format("Failed to create document. There is an account with given username: '%s'",
                    userDto.getUsername()));
        }

        User user = UserDTOConverter.fromUserDtoToUserToSave(userDto);
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public Optional<User> getUserById(final long id) throws UserNotFoundException {
        LOGGER.info(String.format("Performing searching user with give id: %d", id));
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            throw new UserNotFoundException(String.format("User not found with given id: %d", id));

        return userOptional;
    }

    public User updateUser(final UserDTO userDTO, final long id) throws UserNotFoundException, InfoUpdateException {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(id));
        if (!userOptional.isPresent())
            throw new UserNotFoundException(String.format("User not found with given id: %d", id));
        if (usernameExists(userDTO.getUsername()))
            throw new InfoUpdateException(String.format("Exception occurred: such username already taken: %s", userDTO.getUsername()));
        User user = userOptional.get();
        user.setUsername(userDTO.getUsername());

        return mongoOperations.save(user);
    }

    public List<User> getUserList() {
        return mongoOperations.findAll(User.class);
    }

    public User removeUser(final long id) {
        Optional<User> userOptional = getUserById(id);
        if (!userOptional.isPresent())
            throw new UserNotFoundException(String.format("User not found with given id: %d", id));
        User user = userOptional.get();
        mongoOperations.remove(user);

        return user;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
