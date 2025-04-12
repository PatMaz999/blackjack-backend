package pl.bj.blackjack.model;

import pl.bj.blackjack.model.enmus.*;

public class Card {
    CardRank cardRank; //enum
    CardSuit cardSuit; // enum
    int cardValue;

    public Card(int cardRankIndex, int cardSuitIndex) {
        this.cardRank = CardRank.values()[cardRankIndex];
        this.cardSuit = CardSuit.values()[cardSuitIndex];
//        TODO: Ace should be worth 1 or 11
        this.cardValue = cardRankIndex <= 8 ? cardRankIndex + 2 : cardRankIndex != 12 ? 10 : 11;
//        2-10 for standard cards, 10 for jack, queen, king, 11 for ace
    }

}
