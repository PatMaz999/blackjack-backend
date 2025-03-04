package pl.bj.blackjack.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private long id;
    private String username;
    private int points;
}
