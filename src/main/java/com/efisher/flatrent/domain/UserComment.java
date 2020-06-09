package com.efisher.flatrent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Getter
@Setter
@AllArgsConstructor
public class UserComment {
    private String comment;
    private Apartments flat;
}
