package pl.bj.blackjack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Games {

    public Games() {
    }

    public Games(long playerId, int bet) {
        this.playerId = playerId;
        this.bet = bet;
        this.score = 0;
        this.finished = false;
    }

    @Id
    private long id;
    private long playerId;
    private int bet;
    private int score;
    private boolean finished;
}
