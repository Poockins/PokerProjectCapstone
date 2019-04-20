/*
 * 
 * @author rob
 * @version 1.0
 */
package calculator;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class Card {

    private final Rank rank;
    private final Suit suit;

    private ImageIcon cardImage;
    private String imagePath;

    private final static Map<String, Card> CARD_CONTAINER = initContainer();

    private static Map<String, Card> initContainer() {
        final Map<String, Card> container = new HashMap<>();
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                container.put(cardName(rank, suit), new Card(rank, suit));
            }
        }
        return container;
    }

    static Card getCard(final Rank rank, final Suit suit) {
        final Card c = CARD_CONTAINER.get(cardName(rank, suit));
        if (c != null) {
            return c;
        }
        throw new RuntimeException("Not card" + rank + " " + suit);
    }

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    private static String cardName(final Rank rank, final Suit suit) {
        return rank + " of " + suit;
    }

    // public void setSuit(Suit suit){ this.suit = suit; }
}
