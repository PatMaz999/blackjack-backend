package pl.bj.blackjack.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.bj.blackjack.model.Card;
import pl.bj.blackjack.model.enmus.CardOwner;
import pl.bj.blackjack.model.enmus.CardRank;
import pl.bj.blackjack.model.enmus.CardSuit;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CardsOfGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long gameId;
    @Enumerated(EnumType.STRING)
    private CardRank cardRank;
    @Enumerated(EnumType.STRING)
    private CardSuit cardSuit;
    @Enumerated(EnumType.STRING)
    private CardOwner cardOwner;
    private int cardValue;

    public CardsOfGame(long gameId, CardOwner cardOwner, Card card) {
        this.gameId = gameId;
        this.cardRank = card.getCardRank();
        this.cardSuit = card.getCardSuit();
        this.cardValue = card.getCardValue();
        this.cardOwner = cardOwner;
    }
}
