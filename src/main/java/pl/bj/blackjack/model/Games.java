package pl.bj.blackjack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Games {

    public Games(long playerId, int bet) {
        this.playerId = playerId;
        this.bet = bet;
        this.score = 0; //default value is 1850 ???
        this.finished = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long playerId;
    private int bet;
    private int score;
    private boolean finished = false;
    private Boolean win;
    private int opponentScore = 0;
}
