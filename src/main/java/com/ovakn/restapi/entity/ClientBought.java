package com.ovakn.restapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "clientsPurchases")
public class ClientBought {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
}