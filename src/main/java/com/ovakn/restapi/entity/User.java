package com.ovakn.restapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "clients")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String name;

    @Column(unique = true)
    String email;

    @Column
    int balance = 0;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}