package pl.bj.blackjack.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long playerId;
    private int bet;
    private int score;
    private boolean finished = false;
    private Boolean win;
    private int opponentScore = 0;
    @OneToMany
    @JoinColumn(name = "gameId")
    private List<CardsOfGame> cards;

    public Games(long playerId, int bet) {
        this.playerId = playerId;
        this.bet = bet;
        this.score = 0;
        this.finished = false;
    }

}
