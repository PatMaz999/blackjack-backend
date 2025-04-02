package pl.bj.blackjack.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private int points;
    private boolean gameInProgress = false; //don't write to database
    private long currentGameId = -1;
    @OneToMany
    @JoinColumn(name = "playerId")
    private List<Games> games;
}
