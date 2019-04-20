import java.util.ArrayList;

/**
 * This class will produce the probability based off the
 * users input. Poker Calculator.
 * @author Thomas
 *
 */
package calculator;

public class PokerCalculator {
	//THe value of the hand.
	private int handValue;//1-10 . 10 being the most valuable
	//The end users hand
	private ArrayList<Cards> userHand = new ArrayList<Cards>();
	//The cards in the community board
	private ArrayList<Cards> communityCards = new ArrayList<Cards>();

	//the winning probabiltiy result
	private String probabilityStr;


	//The constructor
	public PokerCalculator()
	{
		handValue = 0;//User will only have two hands


	}

	public void setUserHand(Cards card1, Cards card2)
	{
		//adding one of the two cards to the userhand list.
		userHand.add(card1);
		userHand.add(card2);
	}

	public void setCommunityCard()
	{

	}
	//This method will evaluate the users hand to see if it has a good hand
	//such as a flush, high card, pair, three of kind,
	public void evaluateCards()
	{
		//High Card
		//Pair
		//Two Pairs
		//Three of a kind
		//straight (1 2 3 4 5)
		//flush (five same suit)
		//full house (three of kind plus a pair ) (three of same rank and a pair)
		//four of a kind(same ranks)
		//straight flush (same suit in chronological order
		//royal flush( ace king queen jack etc)
		if(hasRoyalFlush())
		{
			handValue = 10;
		}
		else if(hasThreeOfKind())
		{
			handValue = 4;
		}
		else if(hasTwoPairs())
		{
			handValue = 3;
		}
		else if(hasPair())
		{
			handValue = 2;
		}
		else if(hasHighCard())
		{
			handValue = 1;
		}
		else //has no good hand
		{
			handValue = 0;
		}



	}

	/**
	 * Checks to see if the user has a rank card which is anything above 10.
	 * @return
	 */
	public boolean hasHighCard()
	{
		for(Cards card: userHand)
		{
			if(card.getRank().getRankValue()<=10)//has high card
				return true;

		}

		return false;

	}

	/**
	 * Checks to see if you have a pair.
	 * @return
	 */
	public boolean hasPair()
	{
		int num_OfPairs = 0;

		//Comparing hand for pairs/match
		if(userHand.get(0).getRank().getRankValue() ==  userHand.get(1).getRank().getRankValue())
			num_OfPairs++;

		for(Cards userCard: userHand)//checks to see if it has anymore same of a kind or pairs
		{
			//computing the number of matches/pairs with the community board
			for(Cards communityCard: communityCards)
			{
				if(communityCard.getRank().getRankValue() == (userCard.getRank().getRankValue()))
					num_OfPairs++;

			}

		}


		if(num_OfPairs == 1)
			return true;//it has a pair
		else
			return false;//either no pairs or greater than

	}

	/**
	 * Checks to see if you have a pair.
	 * @return
	 */
	public boolean hasTwoPairs()
	{
		int sameKind1 = 0;
		int sameKind2 = 0;
		//Comparing hand for pairs/match
		if(userHand.get(0).getRank().getRankValue() ==  userHand.get(1).getRank().getRankValue())
				return false;//impossible to have two pairs if your only two cards are same



		int card1 = userHand.get(0).getRank().getRankValue();
		int card2 = userHand.get(1).getRank().getRankValue();

			//computing the number of matches/pairs with the community board
			for(Cards communityCard: communityCards)
			{
				if(communityCard.getRank().getRankValue() == card1)
					sameKind1++;

			}

			//computing the number of matches/pairs with the community board
			for(Cards communityCard: communityCards)
			{
				if(communityCard.getRank().getRankValue() == card2)
					sameKind2++;

			}

			if((sameKind2>1 || sameKind1 >1))
				return false;//its either three of kind or has more same cards
			else if((sameKind1+sameKind2) == 2)//two pairs
				return true;
			else
				return false;





	}

	/**
	 * Checks to see if it has three of a kind.
	 * @return
	 */
	public boolean hasThreeOfKind()
	{

		return true;

	}

	/**
	 * Checks to see if it has a royal flush
	 * @return
	 */
	public boolean hasRoyalFlush()
	{
		return true;
	}

	//This will produce the probability of winning.
	public void produceProbability()
	{
		if(handValue == 10)
		{
			probabilityStr = "Win: 99.00% ";

		}
		else if(handValue == 0)
		{
			probabilityStr = "Win: 1.0%";
		}

	}

	/**
	 * The string representation of the poker class
	 */
	public String toString()
	{
		return probabilityStr;
	}

}
