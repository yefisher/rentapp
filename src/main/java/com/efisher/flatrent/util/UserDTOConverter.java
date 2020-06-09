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
}
