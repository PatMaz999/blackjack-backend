package pl.bj.blackjack.service;

import pl.bj.blackjack.model.Card;
import pl.bj.blackjack.model.entity.CardsOfGame;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CardsOfGameMapper {
    private CardsOfGameMapper() {
    }

    public static Set<Card> mapToCardsSet(List<CardsOfGame> cardsOfGames) {
        return cardsOfGames.stream()
                        .map(CardsOfGameMapper::mapToCard)
                        .collect(Collectors.toSet());
    }

    private static Card mapToCard(CardsOfGame cardOfGame) {
        return Card.builder()
                .cardRank(cardOfGame.getCardRank())
                .cardSuit(cardOfGame.getCardSuit())
                .cardValue(cardOfGame.getCardValue())
                .build();
    }
}
