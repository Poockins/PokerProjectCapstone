import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PokerCalculatorTest {


  private Cards userCard1;
  private Cards userCard2;
  private PokerCalculator calcObj = new PokerCalculator();


  private Suit suit1, suit2, suit3, suit4, suit5, suit6, suit7;
  private Rank rank1, rank2, rank3, rank4, rank5, rank6, rank7;

  private Cards card1, card2, card3, card4, card5, card6, card7;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {


  }

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {}


  @Test
  public void testHasTwoOverCard() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.DIAMONDS;
    rank1 = Rank.TEN;

    suit2 = Suit.SPADES;
    rank2 = Rank.ACE;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // community card
    suit3 = Suit.DIAMONDS;
    rank3 = Rank.TWO;
    card3 = new Cards(rank3, suit3);

    suit4 = Suit.SPADES;
    rank4 = Rank.FOUR;
    card4 = new Cards(rank4, suit4);

    suit5 = Suit.HEARTS;
    rank5 = Rank.SEVEN;
    card5 = new Cards(rank5, suit5);



    calcObj.addUserCard(card1);
    calcObj.addUserCard(card2);

    calcObj.addCommunityCard(card3);
    calcObj.addCommunityCard(card4);
    calcObj.addCommunityCard(card5);



    calcObj.probabilityCalculation();
    System.out.println(calcObj);
  }

  @Test
  public void testHasOverCard() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.DIAMONDS;
    rank1 = Rank.NINE;

    suit2 = Suit.SPADES;
    rank2 = Rank.QUEEN;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // community card
    suit3 = Suit.DIAMONDS;
    rank3 = Rank.TWO;
    card3 = new Cards(rank3, suit3);

    suit4 = Suit.SPADES;
    rank4 = Rank.FOUR;
    card4 = new Cards(rank4, suit4);

    suit5 = Suit.HEARTS;
    rank5 = Rank.SIX;
    card5 = new Cards(rank5, suit5);


    calcObj.addUserCard(card1);
    calcObj.addUserCard(card2);

    calcObj.addCommunityCard(card3);
    calcObj.addCommunityCard(card4);
    calcObj.addCommunityCard(card5);



    calcObj.probabilityCalculation();
    System.out.println(calcObj);
  }

  @Test
  public void testHasPair2House() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.DIAMONDS;
    rank1 = Rank.NINE;

    suit2 = Suit.SPADES;
    rank2 = Rank.QUEEN;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // community card
    suit3 = Suit.DIAMONDS;
    rank3 = Rank.NINE;
    card3 = new Cards(rank3, suit3);

    suit4 = Suit.SPADES;
    rank4 = Rank.QUEEN;
    card4 = new Cards(rank4, suit4);

    suit5 = Suit.SPADES;
    rank5 = Rank.SIX;
    card5 = new Cards(rank5, suit5);


    calcObj.addUserCard(card1);
    calcObj.addUserCard(card2);

    calcObj.addCommunityCard(card3);
    calcObj.addCommunityCard(card4);
    calcObj.addCommunityCard(card5);



    calcObj.probabilityCalculation();
    System.out.println(calcObj);
  }

  @Test
  public void testHasNoPairToPair() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.DIAMONDS;
    rank1 = Rank.NINE;

    suit2 = Suit.SPADES;
    rank2 = Rank.QUEEN;

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
    rank5 = Rank.SIX;
    card5 = new Cards(rank5, suit5);

    suit6 = Suit.HEARTS;
    rank6 = Rank.KING;
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



    calcObj.probabilityCalculation();
    System.out.println(calcObj);

  }

  @Test
  public void testHasPocketPair() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.DIAMONDS;
    rank1 = Rank.NINE;

    suit2 = Suit.SPADES;
    rank2 = Rank.NINE;

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
    rank5 = Rank.TEN;
    card5 = new Cards(rank5, suit5);

    suit6 = Suit.HEARTS;
    rank6 = Rank.KING;
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



    calcObj.probabilityCalculation();
    System.out.println(calcObj);
  }

  @Test
  public void testHasInsideStraightDraw() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


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
    rank5 = Rank.TEN;
    card5 = new Cards(rank5, suit5);

    suit6 = Suit.HEARTS;
    rank6 = Rank.KING;
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


    calcObj.probabilityCalculation();
    System.out.println(calcObj);

  }

  @Test
  public void testHasOpenStraightDraw() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


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
    System.out.println(calcObj);

  }



  @Test
  public void testHasFlushDraw() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.DIAMONDS;
    rank1 = Rank.THREE;

    suit2 = Suit.SPADES;
    rank2 = Rank.ACE;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // community card
    suit3 = Suit.DIAMONDS;
    rank3 = Rank.ACE;
    card3 = new Cards(rank3, suit3);

    suit4 = Suit.DIAMONDS;
    rank4 = Rank.JACK;
    card4 = new Cards(rank4, suit4);

    suit5 = Suit.SPADES;
    rank5 = Rank.THREE;
    card5 = new Cards(rank5, suit5);

    suit6 = Suit.HEARTS;
    rank6 = Rank.THREE;
    card6 = new Cards(rank6, suit6);

    suit7 = Suit.DIAMONDS;
    rank7 = Rank.TWO;
    card7 = new Cards(rank7, suit7);

    calcObj.addUserCard(card1);
    calcObj.addUserCard(card2);

    calcObj.addCommunityCard(card3);
    calcObj.addCommunityCard(card4);
    calcObj.addCommunityCard(card5);
    calcObj.addCommunityCard(card6);
    calcObj.addCommunityCard(card7);



    calcObj.probabilityCalculation();
    System.out.println(calcObj);

  }



}
