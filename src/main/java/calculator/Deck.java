/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Cards> deckCards;

    private Deck(final boolean shuff) {
        this.deckCards = initDeck(shuff);
    }

    private Stack<Cards> initDeck(boolean shuff) {
        Stack<Cards> deckCards = new Stack<>();
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                deckCards.push(Cards.getCard(rank, suit));
            }
        }
        if (shuff) {
            Collections.shuffle(deckCards);
        } else {
            Collections.sort(deckCards);
        }
        return deckCards;
    }

    public static Deck newShuffledDeck() {
        return new Deck(true);
    }

    public static Deck newUnshuffledDeck() {
        return new Deck(false);
    }

    public Stack<Cards> getDeckCards() {
        return deckCards;
    }

    public int size() {
        return this.deckCards.size();
    }

    public boolean contains(final Cards card) {
        return this.deckCards.contains(card);
    }

    public Cards card() {

        return this.deckCards.pop();
    }

}
