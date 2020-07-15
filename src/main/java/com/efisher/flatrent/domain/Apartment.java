package com.efisher.flatrent.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "apartments")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
    @Transient
    public static final String SEQUENCE_ID = "apartment_id";
    @Id
    private Long apartmentId;
    private List<String> pictureUrlList;
    private String title;
    private Double price;
    private String description;
    private User owner;
}
