import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PokerCalculatorTest {


	private Cards userCard1;
	private Cards userCard2;
	private PokerCalculator calcObj = new PokerCalculator();


	private Suit suit1, suit2, suit3,suit4, suit5, suit6, suit7;
	private Rank rank1, rank2, rank3, rank4, rank5, rank6, rank7;

	private Cards card1, card2, card3, card4, card5, card6, card7;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{


	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHasFullHouse()
	{

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.DIAMONDS;
		rank1 = Rank.THREE;

		suit2 = Suit.SPADES;
		rank2 = Rank.ACE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//community card
		suit3 = Suit.DIAMONDS;
		rank3 = Rank.ACE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.SPADES;
		rank4 = Rank.JACK;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.THREE;
		card5 = new Cards(rank5, suit5);

		suit6 = Suit.HEARTS;
		rank6 = Rank.THREE;
		card6 = new Cards(rank6, suit6);

		suit7 = Suit.HEARTS;
		rank7 = Rank.TWO;
		card7 = new Cards(rank7, suit7);

		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);
		calcObj.addCommunityCard(card6);
		calcObj.addCommunityCard(card7);


		assertTrue(calcObj.hasfullHouse());
	}

	@Test
	public void testHasStraightFlush()
	{
		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.DIAMONDS;
		rank1 = Rank.THREE;

		suit2 = Suit.SPADES;
		rank2 = Rank.ACE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//community card
		suit3 = Suit.SPADES;
		rank3 = Rank.TEN;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.SPADES;
		rank4 = Rank.JACK;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.KING;
		card5 = new Cards(rank5, suit5);

		suit6 = Suit.SPADES;
		rank6 = Rank.QUEEN;
		card6 = new Cards(rank6, suit6);

		suit7 = Suit.HEARTS;
		rank7 = Rank.TWO;
		card7 = new Cards(rank7, suit7);

		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);
		calcObj.addCommunityCard(card6);
		calcObj.addCommunityCard(card7);

		assertTrue(calcObj.hasStraightFlush());

	}
	@Test
	public void testHasStraight()
	{

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.TWO;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.THREE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//community card
		suit3 = Suit.CLUBS;
		rank3 = Rank.FOUR;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.FIVE;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.SIX;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertTrue(calcObj.hasStraight());
	}

	@Test
	public void testHasHighCard() {

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();

		suit1 = Suit.CLUBS;
		rank1 = Rank.EIGHT;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.THREE;
		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);

		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		assertFalse(calcObj.hasHighCard());//no highcard

		calcObj.resetUserHand();

		suit1 = Suit.CLUBS;
		rank1 = Rank.TEN;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.THREE;
		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);

		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		assertTrue(calcObj.hasHighCard());//yes it has high card or cards
	}

	@Test
	public void testHasPair() {
		//users hand set up
		suit1 = Suit.CLUBS;
		rank1 = Rank.EIGHT;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.EIGHT;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.THREE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.ACE;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.TEN;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertTrue(calcObj.hasPair());


		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.EIGHT;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.NINE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.NINE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.ACE;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.TEN;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertTrue(calcObj.hasPair());

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.EIGHT;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.NINE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.KING;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.ACE;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.TEN;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertFalse(calcObj.hasPair());


	}

	@Test
	public void testHasTwoPairs() {
		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.EIGHT;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.NINE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.NINE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.EIGHT;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.TEN;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertTrue(calcObj.hasTwoPairs());


		//another test
		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.NINE;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.NINE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.EIGHT;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.EIGHT;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.TEN;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertFalse(calcObj.hasTwoPairs());
	}

	@Test
	public void testHasThreeOfKind() {

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.SEVEN;

		suit2 = Suit.DIAMONDS;
		rank2 = Rank.ACE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.ACE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.HEARTS;
		rank4 = Rank.EIGHT;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.SPADES;
		rank5 = Rank.ACE;
		card5 = new Cards(rank5, suit5);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);

		assertTrue(calcObj.hasThreeOfKind());
	}

	@Test
	public void testHasFlush()
	{

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.THREE;

		suit2 = Suit.CLUBS;
		rank2 = Rank.ACE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.ACE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.CLUBS;
		rank4 = Rank.EIGHT;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.CLUBS;
		rank5 = Rank.QUEEN;
		card5 = new Cards(rank5, suit5);

		suit6 = Suit.HEARTS;
		rank6 = Rank.FIVE;
		card6 = new Cards(rank6, suit6);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);
		calcObj.addCommunityCard(card6);

		assertTrue(calcObj.hasFlush());

	}
	@Test
	public void testFourOfAKind()
	{
		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.FOUR;

		suit2 = Suit.SPADES;
		rank2 = Rank.THREE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.THREE;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.CLUBS;
		rank4 = Rank.EIGHT;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.DIAMONDS;
		rank5 = Rank.THREE;

		card5 = new Cards(rank5, suit5);

		suit6 = Suit.HEARTS;
		rank6 = Rank.THREE;
		card6 = new Cards(rank6, suit6);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);
		calcObj.addCommunityCard(card6);

		assertTrue(calcObj.HasFourOfKind());

	}

	@Test
	public void testHasRoyalFlush() {

		calcObj.resetUserHand();
		calcObj.resetCommunityCards();


		suit1 = Suit.CLUBS;
		rank1 = Rank.KING;

		suit2 = Suit.SPADES;
		rank2 = Rank.THREE;

		card1 = new Cards(rank1, suit1);
		card2 = new Cards(rank2, suit2);
		//comunity card
		suit3 = Suit.CLUBS;
		rank3 = Rank.KING;
		card3 = new Cards(rank3, suit3);

		suit4 = Suit.CLUBS;
		rank4 = Rank.TEN;
		card4 = new Cards(rank4, suit4);

		suit5 = Suit.DIAMONDS;
		rank5 = Rank.JACK;

		card5 = new Cards(rank5, suit5);

		suit6 = Suit.HEARTS;
		rank6 = Rank.ACE;
		card6 = new Cards(rank6, suit6);

		suit7 = Suit.HEARTS;
		rank7 = Rank.QUEEN;
		card7 = new Cards(rank7,suit7);


		calcObj.addUserCard(card1);
		calcObj.addUserCard(card2);

		calcObj.addCommunityCard(card3);
		calcObj.addCommunityCard(card4);
		calcObj.addCommunityCard(card5);
		calcObj.addCommunityCard(card6);
		calcObj.addCommunityCard(card7);

		assertTrue(calcObj.hasRoyalFlush());
	}


}
