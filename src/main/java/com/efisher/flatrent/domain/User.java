package com.efisher.flatrent.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private List<UserRole> roles;
    private List<UserComment> userComments;
}
