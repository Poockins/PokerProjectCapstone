package calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//package calculator;

public class Cards implements Comparable<Cards> {

    private final Rank rank;
    private final Suit suit;

    private final static Map<String, Cards> CARD_CONTAINER = initContainer();

    private static Map<String, Cards> initContainer() {
        final Map<String, Cards> container = new HashMap<>();
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                container.put(cardName(rank, suit), new Cards(rank, suit));
            }
        }
        return Collections.unmodifiableMap(container);
    }

    static Cards getCard(final Rank rank, final Suit suit) {
        final Cards card = CARD_CONTAINER.get(cardName(rank, suit));
        if (card != null) {
            return card;
        }
        throw new RuntimeException("NOT a card ! " + rank + " " + suit);
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    private static String cardName(final Rank rank,final Suit suit) {
        return rank + " of " + suit;
    }

    public Cards(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    @Override
    public int compareTo(final Cards val) {
        final int rankCompar = Integer.compare(this.rank.getRankValue(), val.rank.getRankValue());
        return rankCompar != 0 ? rankCompar : Integer.compare(this.suit.getSuitValue(), val.suit.getSuitValue());
    }


}
