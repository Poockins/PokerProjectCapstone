package calculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 * This class will contain some poker odds in the form of boolean methods. The poker odds are the
 * most common that appear in the game of Texas Holdem This will determine the potential that the
 * user has..
 *
 * @author Thomas
 */
 public class PokerChart {
   // poker evaluation object
   private PokerEvaluator evaluator;

   /**
    * The Constructor
    */
   public PokerChart() {
     evaluator = new PokerEvaluator();
   }


   /**
    * It will check the if the users hand has a inside straight draw probability.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the the user has an inside Straight draw
    */
   public boolean hasInsideStraightDraw(ArrayList<Cards> userCards,
       ArrayList<Cards> communityCards) {
     ArrayList<Cards> allCards = new ArrayList<Cards>();
     ArrayList<Cards> chronOrderCards = new ArrayList<Cards>();
     ArrayList<Cards> openEndedCards = new ArrayList<Cards>();
     Cards previousCard;

     allCards.addAll(communityCards);
     allCards.addAll(userCards);


     Collections.sort(allCards);

     ListIterator<Cards> iterator = allCards.listIterator();

     int differenceIndex = 0;
     int previousIndex = 0;
     int i = 0;
     previousCard = (Cards) iterator.next();

     // we get the cards that are in chronological order first, but wont be open ended until next
     // loop.
     while (iterator.hasNext())// begin
     {


       Cards nextCard = (Cards) iterator.next();

       differenceIndex = nextCard.getRank().getRankValue() - previousCard.getRank().getRankValue();


       if (differenceIndex == 1 || differenceIndex == 2)//
       {
         if (i == 0)
           chronOrderCards.add(previousCard);
         chronOrderCards.add(nextCard);// adding card if its chronOrder
         i = 1;
       }
       previousCard = nextCard;

     } // end of while loop


     // now we will verify wether this is open ended ..

     ListIterator<Cards> iterator1 = chronOrderCards.listIterator();

     int differenceIndex1 = 0;

     if (!iterator1.hasNext())
       return false;
     previousCard = (Cards) iterator1.next();

     openEndedCards.add(previousCard);
     while (iterator1.hasNext())// begin
     {

       Cards nextCard = (Cards) iterator1.next();

       differenceIndex1 = nextCard.getRank().getRankValue() - previousCard.getRank().getRankValue();



       if (differenceIndex1 == 1)//
       {
         openEndedCards.add(nextCard);

       } else if (differenceIndex1 == 2)
         openEndedCards.add(nextCard);



       differenceIndex1 = 0;
       previousCard = nextCard;

     } // end of while loop


     if (openEndedCards.size() == 4) {
       // if the users cards is not part of the chronological card then we return false and true if
       // its in the list.
       if (openEndedCards.contains(userCards.get(0)) || openEndedCards.contains(userCards.get(1)))
         return true;
       else
         return false;


     }



     return false;
   }

   /**
    * It checks to see if the user has the open straight draw hand.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the user has an open straight draw.
    */
   public boolean hasOpenStraightDraw(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     ArrayList<Cards> allCards = new ArrayList<Cards>();
     ArrayList<Cards> chronOrderCards = new ArrayList<Cards>();
     ArrayList<Cards> openEndedCards = new ArrayList<Cards>();
     Cards previousCard;

     allCards.addAll(communityCards);
     allCards.addAll(userCards);


     Collections.sort(allCards);

     ListIterator<Cards> iterator = allCards.listIterator();

     int differenceIndex = 0;
     int i = 0;
     previousCard = (Cards) iterator.next();

     // we get the cards that are in chronological order first, but wont be open ended until next
     // loop.
     while (iterator.hasNext())// begin
     {


       Cards nextCard = (Cards) iterator.next();

       differenceIndex = nextCard.getRank().getRankValue() - previousCard.getRank().getRankValue();


       // System.out.println(differenceIndex);
       if (differenceIndex == 1)//
       {
         if (i == 0)
           chronOrderCards.add(previousCard);
         chronOrderCards.add(nextCard);// adding card if its chronOrder
         i = 1;
       }
       previousCard = nextCard;

     } // end of while loop

     // now we will verify wether this is open ended ..

     ListIterator<Cards> iterator1 = chronOrderCards.listIterator();

     int differenceIndex1 = 0;

     if (!iterator1.hasNext())
       return false;

     previousCard = (Cards) iterator1.next();
     openEndedCards.add(previousCard);
     while (iterator1.hasNext())// begin
     {

       Cards nextCard = (Cards) iterator1.next();

       differenceIndex1 = nextCard.getRank().getRankValue() - previousCard.getRank().getRankValue();


       // if this list shows to be not open ended it will return a false.
       if (differenceIndex1 == 1)//
       {
         openEndedCards.add(nextCard);


       } else if (openEndedCards.size() == 4)
         return true;
       else
         return false;


       differenceIndex1 = 0;
       previousCard = nextCard;

     } // end of while loop


     if (openEndedCards.size() == 4) {
       // if the users cards is not part of the chronological card then we return false and true if
       // its in the list.
       if (openEndedCards.contains(userCards.get(0)) || openEndedCards.contains(userCards.get(1)))
         return true;
       else
         return false;


     }
     return false;
   }

   /**
    * checks to see if you have a pair and then potential to have a double pair.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the user has a pocket pair
    */
   public boolean hasPocketPair(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     if (userCards.get(0).getRank().getRankValue() == userCards.get(1).getRank().getRankValue())
     	return true;
     else
     	return false;
   }

   /**
    * This checks to confirm that no pair exists.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the user has no pair.
    */
   public boolean hasNoPairToPair(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     return (!(evaluator.hasPair(userCards, communityCards)));

   }

   /**
    * if it has two pairs that is likely to be a full house
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return boolean value.
    */
   public boolean hasTwoPair2House(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     return (evaluator.hasTwoPairs(userCards, communityCards));


   }



   /**
    * Checks to see if it has a High card that is higher than any other card in the board.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the user has two over cards
    */
   public boolean hasTwoOverCard(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     Cards highCard1, highCard2;
     // if it has high card now well check if its the highest within the community

     highCard1 = userCards.get(0);
     highCard2 = userCards.get(1);

     if (!(highCard1.getRank().getRankValue() >= 10) || !(highCard2.getRank().getRankValue() >= 10))
       return false;// one of the cards is not a high car
     // now we have the high card which is in the hand lets check if that hand is highest in the
     // community
     // check both cards
     for (Cards card : communityCards) {
       if (card.getRank().getRankValue() > highCard1.getRank().getRankValue())
         return false;
       if (card.getRank().getRankValue() > highCard2.getRank().getRankValue())
         return false;
     }


     return true;// we check all possible cards to see if there exists a high card in our hand that
                 // is the highest.



   }

   /**
    * Checks to see if it has a High card that is higher than any other card in the board.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the user has one overcards.
    */
   public boolean hasOverCard(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     Cards highCard;
     // if it has high card now well check if its the highest within the community
     if (evaluator.hasHighCard(userCards, communityCards)) {
       Cards card1 = userCards.get(0);
       Cards card2 = userCards.get(1);
       if (card1.getRank().getRankValue() >= card2.getRank().getRankValue())
         highCard = card1;
       else
         highCard = card2;
       // now we have the high card which is in the hand lets check if that hand is highest in the
       // community

       for (Cards card : communityCards) {
         if (card.getRank().getRankValue() > highCard.getRank().getRankValue())
           return false;
       }
       return true;// we check all possible cards to see if there exists a high card in our hand that
                   // is the highest.

     } else// it does not have a high card so it ends
       return false;

   }

   /**
    * Checks to see if it has a royal flush. The best hand in the game.
    *
    * @param userCards - The users hand
    * @param communityCards - The community cards
    * @return T or F - True if the user has a royal flush
    */
   public boolean hasRoyalFlush(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     return evaluator.hasRoyalFlush(userCards, communityCards);
   }

   /**
    * This checks to see if users hand has cards with the same suit.
    *
    * @return T or F - True if the user has flush draw hand
    */
   public boolean hasFlushDraw(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
     int sameSuitCounter = 0;

     // adding the number of suits that are the same
     if (userCards.get(0).getSuit().getSuitValue() == userCards.get(1).getSuit().getSuitValue()) {
       sameSuitCounter = 2;
       for (Cards card : communityCards) {
         if (userCards.get(0).getSuit().getSuitValue() == card.getSuit().getSuitValue()) {
           sameSuitCounter++;// counting
         }
       }
       // return true
       if (sameSuitCounter == 4)
         return true;
       else
         return false;
     } else {
       int sameSuitCounter1 = 1, sameSuitCounter2 = 1;

       for (Cards card : communityCards) {
         if (userCards.get(0).getSuit().getSuitValue() == card.getSuit().getSuitValue()) {
           sameSuitCounter1++;
         }
       }
       for (Cards card : communityCards) {
         if (userCards.get(1).getSuit().getSuitValue() == card.getSuit().getSuitValue()) {
           sameSuitCounter2++;
         }
       }

       if (sameSuitCounter1 == 4 || sameSuitCounter2 == 4)
         return true;
       else
         return false;


     }


   }
 }
