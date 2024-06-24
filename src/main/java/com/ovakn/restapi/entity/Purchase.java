package com.ovakn.restapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String userName;

    @Column
    int gameId;

    @Column
    String gameName;

    @Column
    String userEmail;

    @Column
    Date purchaseDate = new Date();

    public Purchase(String userName, int gameId, String gameName, String userEmail) {
        this.userName = userName;
        this.gameId = gameId;
        this.gameName = gameName;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Purchase:\n" +
                "id=" + id +
                "\nuserName='" + userName + '\'' +
                "\ngameId=" + gameId +
                "\ngameName='" + gameName + '\'' +
                "\nuserEmail='" + userEmail + '\'' +
                "\npurchaseDate=" + purchaseDate;
    }
}