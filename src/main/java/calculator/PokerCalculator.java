package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * This class will produce the probability based off the
 * users input. Poker Calculator.
 * @author Thomas
 *
 */

 public class PokerCalculator {
   // THe value of the hand.
   private int handValue;// 1-10 . 10 being the most valuable
   // The end users hand
   private ArrayList<Cards> userCards = new ArrayList<Cards>();
   // The cards in the community board
   private ArrayList<Cards> communityCards = new ArrayList<Cards>();

   // the winning probabiltiy result
   private PokerChart pokerChartObj;

   private double probabilityPercent;


   /**
    * The Constructor of the poker Calculator class
    */
   public PokerCalculator() {
     handValue = 0;// User will only have two hands
     pokerChartObj = new PokerChart();

   }

   /**
    * adds cards to the user arraylist
    *
    * @param card
    */
   public void addUserCard(Cards card) {
     // adding one of the two cards to the userhand list.
     userCards.add(card);

   }

   /**
    * adds community cards to the cards list
    *
    * @param card
    */
   public void addCommunityCard(Cards card) {
     communityCards.add(card);
   }

   /**
    * Gets the user cards.
    *
    * @return
    */
   public ArrayList<Cards> getUserCards() {
     return userCards;
   }

   /**
    * Gets the community cards.
    *
    * @return
    */
   public ArrayList<Cards> getCommunityCards() {
     return communityCards;
   }


   /**
    * This method will come up with the odds by expressing it in percentages. Poker odds tell you the
    * probability of winning.
    */
   public void probabilityCalculation() {
     // The outs - the cards that can help you win
     // The lose - the card that will make you lose
     int cardDeck, cardInSuit, cardsUnseen, cardsInHand, cardsDealt;
     double outs, lose;
     cardDeck = 52;// 52 in entire deck/game
     cardInSuit = 13;// cards in each suit
     outs = lose = 0;// intialize to zero
     cardsInHand = userCards.size();
     cardsDealt = communityCards.size();
     // flush draw odds from chart
     if (pokerChartObj.hasFlushDraw(userCards, communityCards)) {

       // now we have the outs
       outs = cardInSuit - 4;// computing outs. 4 is the number of suits that are the same.

       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasTwoPair2House(userCards, communityCards)) {

       // The outs is two because only two cards can make this hand a
       // straight. one in the front or
       // back
       outs = 4;// only two cards can give you a double pair in the deck.
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;

     } else if (pokerChartObj.hasPocketPair(userCards, communityCards)) {

       // The outs is two because only two cards can make this hand a straight. one in the front or
       // back
       outs = 2;// only two cards can give you a double pair in the deck.
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasOpenStraightDraw(userCards, communityCards)) {

       // The outs is two because only two cards can make this hand a straight. one in the front or
       // back
       outs = 4;// each rank has four suits ;
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasInsideStraightDraw(userCards, communityCards)) {

       // The outs is two because only two cards can make this hand a straight. one in the front or
       // back
       outs = 8;// because we have 4 on one end and four at the other end.
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasTwoOverCard(userCards, communityCards)) {


       // The outs is two because only two cards can make this hand a straight. one in the front or
       // back
       outs = 6;// only two cards can give you a double pair in the deck.
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasOverCard(userCards, communityCards)) {


       // The outs is two because only two cards can make this hand a straight. one in the front or
       // back
       outs = 3;// only two cards can give you a double pair in the deck.
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasNoPairToPair(userCards, communityCards)) {


       // The outs is two because only two cards can make this hand a straight. one in the front or
       // back
       outs = 6;// only two cards can give you a double pair in the deck.
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     } else if (pokerChartObj.hasRoyalFlush(userCards, communityCards)) {
       probabilityPercent = 99.0; // best hand you can get.
     } else// have completely nothing therefore by poker chart
     {
       outs = 15;
       // now lets get the unseen cards
       cardsUnseen = cardDeck - cardsInHand - cardsDealt;

       // cards that will make us lose
       lose = cardsUnseen - outs;

       // now lets the odds of getting a winning flush
       // lose to outs ratio
       // we convert the ratio to a percentage.
       probabilityPercent = (outs / (outs + lose)) * 100;
     }

   }

   /**
    * This removes all the cards in the community cards
    */
   public void resetCommunityCards() {
     communityCards.removeAll(communityCards);
   }

   /**
    * This removes all the cards in the users hand
    */
   public void resetUserHand() {
     userCards.removeAll(userCards);
   }

   /**
    * The string representation of the poker class
    */
   public String toString() {
     return "Chances of winning are: " + probabilityPercent;
   }

 }
