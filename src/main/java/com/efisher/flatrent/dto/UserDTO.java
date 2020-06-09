package com.efisher.flatrent.dto;

import com.efisher.flatrent.domain.UserComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.management.relation.Role;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
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
    private List<Role> roles;
    private List<UserComment> comments;
}
