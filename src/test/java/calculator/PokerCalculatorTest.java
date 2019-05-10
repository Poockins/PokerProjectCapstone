package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PokerCalculatorTest {


	private Suit suit1, suit2, suit3, suit4, suit5, suit6, suit7;
	private Rank rank1, rank2, rank3, rank4, rank5, rank6, rank7;

	private Cards card1, card2, card3, card4, card5, card6, card7;

	private PokerCalculator calcObj = new PokerCalculator();

	@Test
	void testProbabilityCalculation() {
		suit1 = Suit.DIAMONDS;
		rank1 = Rank.NINE;

		suit2 = Suit.SPADES;
		rank2 = Rank.ACE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		// community card
		suit3 = Suit.DIAMONDS;
		rank3 = Rank.EIGHT;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.SPADES;
		rank4 = Rank.SIX;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.SEVEN;
		card5 = new Cards(rank5, suit5);

		suit6 = Suit.HEARTS;
		rank6 = Rank.KING;
		card6 = new Cards(rank6, suit6);

		suit7 = Suit.HEARTS;
		rank7 = Rank.SIX;
		card7 = new Cards(rank7, suit7);

		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);
		calcObj.addCommunityCard(card6);
		calcObj.addCommunityCard(card7);

		calcObj.probabilityCalculation();
		String expectedValue = "Chances of winning are: 8.88888888888889";
		assertTrue(expectedValue.equals(calcObj.toString()));
	}

}
