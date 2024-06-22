package com.ovakn.restapi.DTOs;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameDTO {
    String name;
    int price;

    String developer;

    String publisher;

    int quantity;

    int releaseYear;

    String genre;

    String series = "-";
}
