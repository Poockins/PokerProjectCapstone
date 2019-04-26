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
	//THe value of the hand.
	private int handValue;//1-10 . 10 being the most valuable
	//The end users hand
	private ArrayList<Cards> userCards = new ArrayList<Cards>();
	//The cards in the community board
	private ArrayList<Cards> communityCards = new ArrayList<Cards>();

	//the winning probabiltiy result
	private String probabilityStr;


	//The constructor
	public PokerCalculator()
	{
		handValue = 0;//User will only have two hands


	}

	public void addUserCard(Cards card)
	{
		//adding one of the two cards to the userhand list.
		userCards.add(card);

	}

	/**
	 *
	 * @param card
	 */
	public void addCommunityCard(Cards card)
	{
		communityCards.add(card);
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
		else if(hasStraightFlush())
		{
			handValue = 9;
		}
		else if(HasFourOfKind())
		{
			handValue = 8;
		}
		else if(hasfullHouse())
		{
			handValue = 7;
		}
		else if(hasFlush())
		{
			handValue = 6;
		}
		else if(hasStraight())
		{
			handValue = 5;
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



	}


	/**
	 * Checks to see if the user has a rank card which is anything above 10.
	 * @return
	 */
	public boolean hasHighCard()
	{
		for(Cards card: userCards)
		{
			if(card.getRank().getRankValue()>=10)//has high card
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
		int num_OfPairs, num_OfPairs1;
		num_OfPairs = num_OfPairs1 = 0;
		Cards userCard1, userCard2;

		//Comparing hand for pairs/match
		if(userCards.get(0).getRank().getRankValue() ==  userCards.get(1).getRank().getRankValue())
		{
			num_OfPairs++;//since the users card match between each other

			userCard1 = userCards.get(0);

			//computing the number of matches/pairs with the community board
			for(Cards communityCard: communityCards)
			{
				if(communityCard.getRank().getRankValue() == (userCard1.getRank().getRankValue()))
					num_OfPairs++;

			}

		}
		else
		{
			userCard1 = userCards.get(0);
			userCard2 = userCards.get(1);
			//computing the number of matches/pairs with the community board
			for(Cards communityCard: communityCards)
			{
				if(communityCard.getRank().getRankValue() == (userCard1.getRank().getRankValue()))
					num_OfPairs++;

			}
			//computing the number of matches/pairs with the community board
			for(Cards communityCard: communityCards)
			{
				if(communityCard.getRank().getRankValue() == (userCard2.getRank().getRankValue()))
					num_OfPairs1++;

			}


		}





		if(num_OfPairs == 1)
			return true;//it has a pair
		else if(num_OfPairs1 ==1)
			return true;
		else
			return false;//either no pairs or greater than

	}

	/**
	 * Checks to see if it has a straight, but with all the same suits.
	 * @return
	 */
	public boolean hasStraightFlush()
	{

		//does the user have a straight.
		if(hasStraight() && hasFlush())
			return true;
		else
			return false;


	}

	public boolean hasfullHouse()
	{
		if(hasThreeOfKind() && hasPair())
			return true;
		else
			return false;

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
		if(userCards.get(0).getRank().getRankValue() ==  userCards.get(1).getRank().getRankValue())
				return false;//impossible to have two pairs if your only two cards are same



		int card1 = userCards.get(0).getRank().getRankValue();
		int card2 = userCards.get(1).getRank().getRankValue();

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
	 * It checks to see if the user has a straight. Basically card or cards
	 * @return
	 */
	public boolean hasStraight()
	{
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

		while( iterator.hasNext())//begin
		{


			Cards nextCard = (Cards) iterator.next();

			differenceIndex = nextCard.getRank().getRankValue() - previousCard.getRank().getRankValue();

			previousCard = nextCard;

			if(differenceIndex == 1)//
				chronOrderCards.add(nextCard);//adding card if its chronOrder


		}//end of while loop


			if(chronOrderCards.size() >= 5)
			{
				//if the users cards is not part of the chronological card then we return false and true if its in the list.
				if(chronOrderCards.contains(userCards.get(0)) || chronOrderCards.contains(userCards.get(1)))
					return true;
				else
					return false;


			}



		return false;
	}
	/**
	 * Checks to see if it has four of a kind in the texas holden game.
	 */
	public boolean HasFourOfKind()
	{
		int sameKind = 0;//initialized to zero.
		int sameKind1 = 0;
		//checks hand. if both hands have matching rank then we can just use one of them to
		//to check for more matches in the community card
		if(userCards.get(0).getRank().getRankValue() ==  userCards.get(1).getRank().getRankValue())
		{
			sameKind+= 2;//2 same kind.

				for(Cards communityCard: communityCards)
				{
					if(userCards.get(0).getRank().getRankValue() == communityCard.getRank().getRankValue())
						sameKind++;//

				}


		}
		else{//this will check to each hand for matches since the hands have no matching
			 Cards firstCard = userCards.get(0);
			 Cards secondCard = userCards.get(1);

			 //evaluate first card
			 sameKind++;//adding because of first hand
			 for(Cards communityCard: communityCards)
			 {
				 if(firstCard.getRank().getRankValue() == communityCard.getRank().getRankValue())
				 sameKind++;

			 }


			 	sameKind1++;
			 	for(Cards communityCard: communityCards)
				{
					 if(secondCard.getRank().getRankValue() == communityCard.getRank().getRankValue())
					 sameKind1++;

				}


		}

		 if((sameKind>1 && sameKind1>1))//impossible to have just a three of a kind.
				return false;
		 else if(sameKind == 4)//three of kind
			 return true;
		 else if (sameKind1 == 4)
			 return true; //three of a kind

		 return false;
	}

	/**
	 * Checks to see if it has three of a kind.
	 * @return
	 */
	public boolean hasThreeOfKind()
	{
		int sameKind = 0;//initialized to zero.
		int sameKind1 = 0;
		//checks hand. if both hands have matching rank then we can just use one of them to
		//to check for more matches in the community card
		if(userCards.get(0).getRank().getRankValue() ==  userCards.get(1).getRank().getRankValue())
		{
			sameKind+= 2;//2 same kind.

				for(Cards communityCard: communityCards)
				{
					if(userCards.get(0).getRank().getRankValue() == communityCard.getRank().getRankValue())
						sameKind++;//

				}


		}
		else{//this will check to each hand for matches since the hands have no matching
			 Cards firstCard = userCards.get(0);
			 Cards secondCard = userCards.get(1);

			 //evaluate first card
			 sameKind++;//adding because of first hand
			 for(Cards communityCard: communityCards)
			 {
				 if(firstCard.getRank().getRankValue() == communityCard.getRank().getRankValue())
				 sameKind++;

			 }


			 	sameKind1++;
			 	for(Cards communityCard: communityCards)
				{
					 if(secondCard.getRank().getRankValue() == communityCard.getRank().getRankValue())
					 sameKind1++;

				}


		}
		if(sameKind==3 || sameKind1==3)
			return true;
		else
		 return false;

	}

	/**
	 * checks to see if there are five cards with the same suit
	 * @return
	 */
	public boolean hasFlush()
	{
		int suitCounter = 0;

		Cards userCard1 = userCards.get(0);
		Cards userCard2 = userCards.get(1);

		//comparing first card from user.
		if(userCard1.getSuit().getSuitValue() == userCard2.getSuit().getSuitValue())
		{	//since both match(suits) we have two same suits therefore
			suitCounter += 2;

			for(Cards communityCard: communityCards)
			{
				if(userCard1.getSuit().getSuitValue() == communityCard.getSuit().getSuitValue())
				suitCounter++;

			}
				if(suitCounter == 5)
					return true;
		}
		else//will check both cards of user to see if it has a five cards of same suit
		{
			//first card
			suitCounter++;
			//first card
			for(Cards communityCard: communityCards)
			{
				if(userCard1.getSuit().getSuitValue() == communityCard.getSuit().getSuitValue())
				suitCounter++;

			}

				//if five is found then return use
				if(suitCounter == 5)
					return true;
				else
					suitCounter = 0;//reset it for the second card user

				//second card
				suitCounter++;
			//checking second card
			for(Cards communityCard: communityCards)
			{
				if(userCard2.getSuit().getSuitValue() == communityCard.getSuit().getSuitValue())
						suitCounter++;

			}
				//if five same suit is found return true
				if(suitCounter == 5)
					return true;
				else
					suitCounter =0;

		}

		return false;//no same suits of five


	}

	/**
	 * Checks to see if it has a royal flush
	 * @return
	 */
	public boolean hasRoyalFlush()
	{
		//boolean representation of the cards
		boolean aceCard, kingCard, queenCard, jackCard, tenCard;//royal flush
		aceCard = kingCard = queenCard = jackCard = tenCard =false;

		//The arraylist of cards.
		ArrayList<Cards> allCards = new ArrayList<Cards>();
		ArrayList<Cards> royalCards = new ArrayList<Cards>();

		//adding all the cards for evaluation.
		allCards.addAll(communityCards);
		allCards.addAll(userCards);

		//checks to see if all the cards are in for a royal flush possibility.
		for(Cards card: allCards)
		{
			if(card.getRank().getRankValue() == 10)
			{
				tenCard = true;
				royalCards.add(card);//adding the card
			}
			else if(card.getRank().getRankValue() == 11)
			{
				jackCard = true;
				royalCards.add(card);//adding the card
			}
			else if(card.getRank().getRankValue() == 12)
			{
				queenCard = true;
				royalCards.add(card);//adding the card
			}
			else if(card.getRank().getRankValue() == 13)
			{
				kingCard = true;
				royalCards.add(card);//adding the card
			}
			else if(card.getRank().getRankValue() == 14)
			{
				aceCard = true;
				royalCards.add(card);//adding the card
			}

		}

			if(tenCard && jackCard && queenCard && kingCard && aceCard)
			{
				if(royalCards.contains(userCards.get(0)))
						return true;
				else if(royalCards.contains(userCards.get(1)))
						return true;
				else
					return false;
			}
			else
				return false;
	}

	//This will produce the probability of winning.
	public void produceProbability()
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
		switch (handValue)
		{
		 case 0:

			 break;
		 case 1://high card
			 probabilityStr = "Win: 3.0%";
			 break;
		 case 2:// one pair

			 break;

		 case 3://two pairs

			 break;
		 case 4://three of a kind

			 break;
		 case 5://straight (1 2 3 4 5)

			 break;
		 case 6: //flush

			 break;
		 case 7://full house

			 break;
		 case 8://four of a kind

			 break;
		 case 9://straight flush

			 break;
		 case 10:// royal flush
			 probabilityStr = "Win: 99.00% ";
			 break;


		}


	}

	/**
	 * This removes all the cards in the community cards
	 */
	public void resetCommunityCards()
	{
		communityCards.removeAll(communityCards);
	}

	/**
	 * This removes all the cards in the users hand
	 */
	public void resetUserHand()
	{
		userCards.removeAll(userCards);
	}

	/**
	 * The string representation of the poker class
	 */
	public String toString()
	{
		return probabilityStr;
	}

}
