package com.efisher.flatrent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "apartments")
@Getter
@Setter
@AllArgsConstructor
public class Apartments {
    @Id
    private String flatId;
    private String title;
    private Double price;
    private String description;
    private User owner;
}
