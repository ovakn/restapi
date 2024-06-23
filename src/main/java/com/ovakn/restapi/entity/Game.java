package com.ovakn.restapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(nullable = false)
    int price;

    @Column
    String developer;

    @Column
    String publisher;

    @Column(length = 3, nullable = false)
    String availability;

    @Column
    int quantity;

    @Column(nullable = false)
    int releaseYear;

    @Column
    String genre;

    @Column
    String series = "-";

    @Column
    boolean isBought = false;

    public Game(String name, int price, String developer, String publisher, int quantity, int releaseYear,
                String genre, String series) {
        this.name = name;
        this.price = price;
        this.developer = developer;
        this.publisher = publisher;
        this.quantity = quantity;
        if (quantity == 0) {
            this.availability = "Нет";
        } else {
            this.availability = "Да";
        }
        this.releaseYear = releaseYear;
        this.genre = genre;
        if (Objects.equals(series, "")) {
            this.series = "-";
        } else {
            this.series = series;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", developer='" + developer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", availability='" + availability + '\'' +
                ", quantity=" + quantity +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", series='" + series + '\'' +
                ", isBought=" + isBought +
                '}';
    }
}