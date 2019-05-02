package calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CardsTests {
  @Test
  @DisplayName("Convert database string to card")
  void stringToCardTest() {
    String dbString = "TWO::SPADES";
    Cards created = Cards.stringToCard(dbString);

    assertNotNull(created);
    assertEquals(Rank.TWO, created.getRank());
    assertEquals(Suit.SPADES, created.getSuit());
  }

  @Test
  @DisplayName("Convert to database friendly string")
  void toDataStringTest() {
    Cards card = new Cards(Rank.TWO, Suit.SPADES);
    assertEquals("TWO::SPADES", card.toDataString());
  }
}
