/**
 * This Class can evaluate a users hand efficiently to determine the hand that it currently has this
 * is mainly used to support the poker chart process. The poker chart determines what potential
 * users has.
 *
 * @author Thomas
 */

package calculator;

import java.util.*;

public class PokerEvaluator {

  // Evaluator String
  private String EvaluationStr;


  /**
   * The constructor for the class.
   */
  public PokerEvaluator() {

    EvaluationStr = "";
  }


  /**
   * Checks to see if the user has a rank card which is anything above 10. A high card is ten, jack,
   * queen, king and ace
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if user has high card
   */
  public boolean hasHighCard(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    for (Cards card : userCards) {
      if (card.getRank().getRankValue() >= 10)// has high card
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Checks to see if the user, not the community cards, has a pair.
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has a pair
   */
  public boolean hasPair(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    return pairCount(userCards, communityCards) == 1;
  }

  /**
   * Checks to see if it has a straight, but with all the same suits. Known as a straight flush
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has a straight flush
   */
  public boolean hasStraightFlush(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    return (hasStraight(userCards, communityCards) && hasFlush(userCards, communityCards));
  }

  /**
   * Checks to see if the user has a full house.
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has full house
   */
  public boolean hasFullHouse(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    boolean playerUsed = false; // At least one player card must be used in the hand
    int[] foundRanks = new int[13];

    int[] userRanks = findRankCount(foundRanks, userCards);
    int[] totalRanks = findRankCount(userRanks, communityCards);

    boolean pairFound = false;
    boolean threeFound = false;

    for (int i = 0; i < totalRanks.length; i++) {
      if (totalRanks[i] == 2) {
        pairFound = true;
        if (userRanks[i] > 0) {
          playerUsed = true;
        }
      } else if (totalRanks[i] == 3) {
        threeFound = true;
        if (userRanks[i] > 0) {
          playerUsed = true;
        }
      }
    }

    return playerUsed && pairFound && threeFound;
  }

  /**
   * Checks to see if the user has two pairs.
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has two pair hand.
   */
  public boolean hasTwoPairs(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    return pairCount(userCards, communityCards) == 2;
  }

  /**
   * It checks to see if the user has a straight. Basically card or cards
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has has a straight.
   */
  public boolean hasStraight(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    ArrayList<Cards> allCards = new ArrayList<Cards>();
    ArrayList<Cards> chronOrderCards = new ArrayList<Cards>();
    Cards previousCard;

    allCards.addAll(communityCards);
    allCards.addAll(userCards);


    Collections.sort(allCards);

    ListIterator<Cards> iterator = allCards.listIterator();

    int differenceIndex = 0;

    previousCard = (Cards) iterator.next();
    chronOrderCards.add(previousCard);

    while (iterator.hasNext())// begin
    {


      Cards nextCard = (Cards) iterator.next();

      differenceIndex = nextCard.getRank().getRankValue() - previousCard.getRank().getRankValue();

      previousCard = nextCard;

      if (differenceIndex == 1)//
      {
        chronOrderCards.add(nextCard);// adding card if its chronOrder
      }


    } // end of while loop


    if (chronOrderCards.size() >= 5) {
      // if the users cards is not part of the chronological card then we return false and true if
      // its in the list.
      if (chronOrderCards.contains(userCards.get(0)) || chronOrderCards.contains(userCards.get(1))) {
        return true;
      } else {
        return false;
      }
    }

    return false;
  }

  /**
   * Checks to see if it has four of a kind in the Texas holden game.
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F if the user has four of a kind.
   */
  public boolean hasFourOfKind(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    boolean result = false;
    int[] foundRanks = new int[13];

    int[] userRanks = findRankCount(foundRanks, userCards);
    int[] totalRanks = findRankCount(userRanks, communityCards);

    for (int i = 0; i < totalRanks.length; i++) {
      if (totalRanks[i] == 4 && userRanks[i] > 0) {
        result = true;
      }
    }

    return result;
  }

  /**
   * Checks to see if it has three of a kind.
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has three of a kind.
   */
  public boolean hasThreeOfKind(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    boolean result = false;
    int[] foundRanks = new int[13];

    int[] userRanks = findRankCount(foundRanks, userCards);
    int[] totalRanks = findRankCount(userRanks, communityCards);

    for (int i = 0; i < totalRanks.length; i++) {
      if (totalRanks[i] == 3 && userRanks[i] > 0) {
        result = true;
      }
    }

    return result;
  }

  /**
   * checks to see if there are five cards with the same suit
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has a flush
   */
  public boolean hasFlush(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    int[] suitCount = new int[4];
    int[] userSuits = findSuitCount(suitCount, userCards);
    int[] totalSuits = findSuitCount(userSuits, communityCards);
    boolean isFlush = false;

    for (int i = 0; i < totalSuits.length; i++) {
      if (totalSuits[i] >= 5 && userSuits[i] > 0) {
        isFlush = true;
      }
    }
    return isFlush;
  }

  /**
   * Checks to see if it has a royal flush
   *
   * @param userCards - The users hand
   * @param communityCards - The community cards
   * @return T or F - True if the user has a royal flush
   */
  public boolean hasRoyalFlush(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    HashMap<Integer, ArrayList<Cards>> royalValues = new HashMap<>();
    int[] suitCount = new int[4];
    boolean isRoyalFlush = false;

    HashMap<Integer, ArrayList<Cards>> userResults = findRoyals(royalValues, userCards);
    HashMap<Integer, ArrayList<Cards>> totalResults = findRoyals(userResults, communityCards);

    if (totalResults.size() == 5 && !userResults.isEmpty()) {
      for (ArrayList<Cards> found : totalResults.values()) {
        suitCount = findSuitCount(suitCount, found);
      }

      for (int count : suitCount) {
        if (count >= 5) {
          isRoyalFlush = true;
        }
      }
    }
    return isRoyalFlush;
  }

  /**
   * Populates a given Hashmap with royal ranks and found cards of that rank
   *
   * @param royalValues Hashmap of possibly existing ranks and found cards of that rank
   * @param cards Cards to check through
   * @return Populated Hashmap of card ranks and associated cards
   */
  private HashMap<Integer, ArrayList<Cards>> findRoyals(HashMap<Integer, ArrayList<Cards>> royalValues, ArrayList<Cards> cards) {
    HashMap<Integer, ArrayList<Cards>> found = new HashMap<>();
    found.putAll(royalValues);
    for (Cards check : cards) {
      int value = check.getRank().getRankValue();

      if (value >= 10 && value <= 14) {
        if (royalValues.containsKey(value)) {
          ArrayList<Cards> existing = royalValues.get(value);
          ArrayList<Cards> foundCards = new ArrayList<>();
          foundCards.addAll(existing);
          foundCards.add(check);
          found.put(value, existing);
        } else {
          ArrayList<Cards> newCard = new ArrayList<>();
          newCard.add(check);
          found.put(value, newCard);
        }
      }
    }

    return found;
  }

  /**
   * Populates an array representing how many cards of a suit have been found
   * @param found previously found suit counts
   * @param cards Cards to check through
   * @return array representing how many cards of a suit have been found
   */
  private int[] findSuitCount(int[] found, ArrayList<Cards> cards) {
    int[] result = Arrays.copyOf(found, found.length);
    for (Cards card : cards) {
      int suitIndex = card.getSuit().getSuitValue() - 1;
      result[suitIndex]++;
    }

    return result;
  }

  /**
   * Populates an array with number of cards found per rank
   *
   * @param found array of card counts already found
   * @param cards Cards to check through
   * @return array of card counts found per rank
   */
  private int[] findRankCount(int[] found, ArrayList<Cards> cards) {
    int[] result = Arrays.copyOf(found, found.length);
    for (Cards card : cards) {
      int rankIndex = card.getRank().getRankValue() - 2; // Rank values start at 2 with ace being 14
      result[rankIndex]++;
    }

    return result;
  }

  private int pairCount(ArrayList<Cards> userCards, ArrayList<Cards> communityCards) {
    int[] found = new int[13];
    int pairs = 0;
    boolean playerUsed = false;

    int[] userFound = findRankCount(found, userCards);
    int[] totalFound = findRankCount(userFound, communityCards);

    for (int i = 0; i < totalFound.length; i++) {
      if (totalFound[i] == 2) {
        pairs++;
        if (userFound[i] > 0) {
          playerUsed = true;
        }
      }
    }

//  Only count pairs if at least one of the cards is held by the player
    if (playerUsed) {
      return pairs;
    } else {
      return 0;
    }
  }

  /**
   * The string representation of the poker class
   */
  public String toString() {
    return EvaluationStr;
  }

}
