package pl.bj.blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Cards {
    private List<Card> cards = new ArrayList<>();

    private Cards() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 12; j++)
                cards.add(new Card(j,i));
    }

//    requires list of excluded cards
    public static Card getUnique(Set<Card> currentCards) {
        Random rand = new Random();
        Cards cardSet = new Cards();
        return cardSet.cards.stream()
                .filter(n ->!currentCards.contains(n))
                .skip(rand.nextInt(cardSet.cards.size())) //skip to random card
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No cards left"));
    }
}
