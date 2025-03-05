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
//    @GeneratedValue
    private long id;
    private String username;
    private int points;
    private boolean gameInProgress; //don't write to database
    private long currentGameId;
    @OneToMany
    @JoinColumn(name = "gameId") //???
    private List<Games> games;
}
