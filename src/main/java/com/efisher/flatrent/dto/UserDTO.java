package com.efisher.flatrent.dto;

import com.efisher.flatrent.domain.UserComment;
import com.efisher.flatrent.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private List<UserRole> roles;
    private List<UserComment> comments;
}
