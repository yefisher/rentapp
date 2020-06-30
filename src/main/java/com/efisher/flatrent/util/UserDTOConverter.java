package com.efisher.flatrent.util;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.dto.UserDTO;

public final class UserDTOConverter {

    public static User fromUserDto(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .roles(userDTO.getRoles())
                .userComments(userDTO.getComments())
                .build();
    }

    public static User fromUserDtoToUpdate(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .roles(userDTO.getRoles())
                .build();
    }

    public static User fromUserDtoToUserToSave(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .build();

    }
}
