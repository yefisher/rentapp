package com.efisher.flatrent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Getter
@Setter
@AllArgsConstructor
public class UserRole {
    private String role;
}
