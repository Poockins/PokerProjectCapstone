package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PokerEvaluatorTest {
  private PokerEvaluator calcEval = new PokerEvaluator();
  private PokerCalculator calcObj = new PokerCalculator();

  private Suit suit1, suit2, suit3, suit4, suit5, suit6, suit7;
  private Rank rank1, rank2, rank3, rank4, rank5, rank6, rank7;

  private Cards card1, card2, card3, card4, card5, card6, card7;


  @Test
  public void testHasStraightFlush() {
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
    rank3 = Rank.SIX;
    card3 = new Cards(rank3, suit3);

    suit4 = Suit.SPADES;
    rank4 = Rank.JACK;
    card4 = new Cards(rank4, suit4);

    suit5 = Suit.DIAMONDS;
    rank5 = Rank.FIVE;
    card5 = new Cards(rank5, suit5);

    suit6 = Suit.DIAMONDS;
    rank6 = Rank.FOUR;
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

    assertTrue(calcEval.hasStraightFlush(calcObj.getUserCards(), calcObj.getCommunityCards()));

  }

  @Test
  public void testHasFullHouse() {

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


    assertTrue(calcEval.hasFullHouse(calcObj.getUserCards(), calcObj.getCommunityCards()));
  }

  @Test
  public void testHasStraight() {

    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.CLUBS;
    rank1 = Rank.TWO;

    suit2 = Suit.DIAMONDS;
    rank2 = Rank.THREE;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // community card
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

    assertTrue(calcEval.hasStraight(calcObj.getUserCards(), calcObj.getCommunityCards()));
  }

  @Test
  public void testHasHighCard() {


    calcObj.resetUserHand();

    suit1 = Suit.CLUBS;
    rank1 = Rank.TEN;

    suit2 = Suit.DIAMONDS;
    rank2 = Rank.THREE;
    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);

    calcObj.addUserCard(card1);
    calcObj.addUserCard(card2);

    assertTrue(calcEval.hasHighCard(calcObj.getUserCards(), calcObj.getCommunityCards()));// yes it
    // has
    // high
    // card or
    // cards
  }

  @Test
  public void testHasPair() {
    // users hand set up
    suit1 = Suit.CLUBS;
    rank1 = Rank.EIGHT;

    suit2 = Suit.DIAMONDS;
    rank2 = Rank.EIGHT;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // comunity card
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

    assertTrue(calcEval.hasPair(calcObj.getUserCards(), calcObj.getCommunityCards()));


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
    // comunity card
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

    assertTrue(calcEval.hasTwoPairs(calcObj.getUserCards(), calcObj.getCommunityCards()));

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
    // comunity card
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

    assertTrue(calcEval.hasThreeOfKind(calcObj.getUserCards(), calcObj.getCommunityCards()));
  }

  @Test
  public void testHasFlush() {

    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.CLUBS;
    rank1 = Rank.THREE;

    suit2 = Suit.CLUBS;
    rank2 = Rank.ACE;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // comunity card
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

    assertTrue(calcEval.hasFlush(calcObj.getUserCards(), calcObj.getCommunityCards()));

  }

  @Test
  public void testFourOfAKind() {
    calcObj.resetUserHand();
    calcObj.resetCommunityCards();


    suit1 = Suit.CLUBS;
    rank1 = Rank.FOUR;

    suit2 = Suit.SPADES;
    rank2 = Rank.THREE;

    card1 = new Cards(rank1, suit1);
    card2 = new Cards(rank2, suit2);
    // comunity card
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

    assertTrue(calcEval.hasFourOfKind(calcObj.getUserCards(), calcObj.getCommunityCards()));

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
    // comunity card
    suit3 = Suit.DIAMONDS;
    rank3 = Rank.FIVE;
    card3 = new Cards(rank3, suit3);

    suit4 = Suit.CLUBS;
    rank4 = Rank.TEN;
    card4 = new Cards(rank4, suit4);

    suit5 = Suit.CLUBS;
    rank5 = Rank.JACK;

    card5 = new Cards(rank5, suit5);

    suit6 = Suit.CLUBS;
    rank6 = Rank.ACE;
    card6 = new Cards(rank6, suit6);

    suit7 = Suit.CLUBS;
    rank7 = Rank.QUEEN;
    card7 = new Cards(rank7, suit7);


    calcObj.addUserCard(card1);
    calcObj.addUserCard(card2);

    calcObj.addCommunityCard(card3);
    calcObj.addCommunityCard(card4);
    calcObj.addCommunityCard(card5);
    calcObj.addCommunityCard(card6);
    calcObj.addCommunityCard(card7);

    assertTrue(calcEval.hasRoyalFlush(calcObj.getUserCards(), calcObj.getCommunityCards()));
  }
}
