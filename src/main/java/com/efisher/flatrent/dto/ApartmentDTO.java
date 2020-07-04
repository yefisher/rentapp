package com.efisher.flatrent.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApartmentDTO {
    private String id;
    private List<String> pictureUrlList;
    private String title;
    private Double price;
    private String description;
    private UserDTO owner;
}
