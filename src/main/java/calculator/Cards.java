/**
 * Card class to represent a single card in a hand
 */
package calculator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cards implements Comparable<Cards> {

  private final Rank rank;
  private final Suit suit;

  public Cards(final Rank rank, final Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }


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

  /**
   * Creates a Card from a database formatted string
   *
   * @param data String representation of the card
   * @return Card created from database string
   */
  public static Cards stringToCard(String data) {
    String[] split = data.split("::'");
    Rank rank = Rank.valueOf(split[0]);
    Suit suit = Suit.valueOf(split[1]);
    return new Cards(rank, suit);
  }

  private static String cardName(final Rank rank, final Suit suit) {
    return rank + " of " + suit;
  }

  /**
   * Used for storing cards in the database as strings
   *
   * @return database friendly string representation of the card
   */
  public String toDataString() {
    return String.format("%s::%s", this.rank, this.suit);
  }

  @Override
  public String toString() {
    return String.format("%s of %s", this.rank, this.suit);
  }

  @Override
  public int compareTo(final Cards val) {
    final int rankCompar = Integer.compare(this.rank.getRankValue(), val.rank.getRankValue());
    return rankCompar != 0 ? rankCompar
        : Integer.compare(this.suit.getSuitValue(), val.suit.getSuitValue());
  }
}