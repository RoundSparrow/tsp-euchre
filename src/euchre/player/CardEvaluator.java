package euchre.player;

import java.util.LinkedList;


/**
 * A class that is used to evaluate cards.
 * 
 * @author Neil MacBay (nmmacbay)
 */
public class CardEvaluator {
	
	/**
	 * Given the trump and lead suit will determine the integer value of a card (Higher values winning).
	 * 
	 * Values of all cards:
	 * Right Bower = 13
	 * Left Bower = 12
	 * Ace (Trump) = 11
	 * King (Trump) = 10
	 * Queen (Trump) = 9
	 * Ten (Trump) = 8
	 * Nine (Trump) = 7
	 * Ace (Lead) = 6
	 * King (Lead) = 5
	 * Queen (Lead) = 4
	 * Jack (Lead) = 3
	 * Ten (Lead) = 2
	 * Nine (Lead) = 1
	 * Ace (Off-Suite) = 0
	 * King (Off-Suite) = -1
	 * Queen (Off-Suite) = -2
	 * Jack (Off-Suite) = -3
	 * Then (Off-Suite) = -4
	 * Nine (Off-Suite) = -5
	 * 
	 * @return The value of the given card.
	 */
	public static int cardValue(char trump, char lead, Card card){
		Card TRB = new Card('j', trump);
		Card TLB = new Card('j', trump);
		Card TA = new Card('a', trump);
		Card TK = new Card('k', trump);
		Card TQ = new Card('q', trump);
		Card T10 = new Card('0', trump);
		Card T9 = new Card('9', trump);
		Card LA = new Card('a', lead);
		Card LK = new Card('k', lead);
		Card LQ = new Card('q', lead);
		Card LJ = new Card('j', lead);
		Card L10 = new Card('0', lead);
		Card L9 = new Card('9', lead);
		int cardValue;
		if (card.compareTo(TRB)==0){ 
			cardValue = 13;
		}
		else if (card.compareTo(TLB)==0){
			cardValue = 12;
		}
		else if (card.compareTo(TA)==0){
			cardValue = 11;
		}
		else if (card.compareTo(TK)==0){
			cardValue = 10;
		}
		else if (card.compareTo(TQ)==0){
			cardValue = 9;
		}
		else if (card.compareTo(T10)==0) {
			cardValue = 8;
		}
		else if (card.compareTo(T9)==0) {
			cardValue = 7;
		}
		else if (card.compareTo(LA)==0) {
			cardValue = 6;
		}
		else if (card.compareTo(LK)==0) {
			cardValue = 5;
		}
		else if (card.compareTo(LQ)==0) {
			cardValue = 4;
		}
		else if (card.compareTo(LJ)==0) {
			cardValue = 3;
		}
		else if (card.compareTo(L10)==0) {
			cardValue = 2;
		}
		else if (card.compareTo(L9)==0) {
			cardValue = 1;
		}
		else{ //not lead or trump.
			switch (card.value){
			case '9':
				cardValue = -5;
				break;
			case '0':
				cardValue = -4;
				break;
			case 'j':
				cardValue = -3;
				break;
			case 'q':
				cardValue = -2;
				break;
			case 'k':
				cardValue = -1;
				break;
			default: //case 'a'
				cardValue = 0;
				break;
			}
		}
		return cardValue;
	}
	
	/**
	 * Given an array of cards(represents a hand) along with the trump and lead suits, picks the highest value card. Or null if hand is empty.
	 * 
	 * @param trump The suite that is trump.
	 * @param lead The suite that is lead.
	 * @param hand The array of cards to find the highest trump card in.
	 * @return Highest value card in hand, or null if the hand is empty.
	 */
	public static Card highestCardInHand(char trump, char lead, Card[] hand){
		Card currentHighest = null;
		int currentHValue = -6; //every card evaluation is higher in value
		for (Card card: hand){
			if (card != null){
				if (cardValue(trump, lead, card) > currentHValue){
					currentHValue = cardValue(trump, lead, card);
					currentHighest = card;
				}
			}
		}
		return currentHighest;
	}
	
	/**
	 * Given an array of cards(represents a hand) along with the trump and lead suits, picks the highest lead card. Null if no lead exist.
	 * 
	 * @param trump The suite that is trump.
	 * @param lead The suite that is lead.
	 * @param hand The array of card to find the highest lead card in.
	 * @return The highest lead car, or null if no lead cards exist.
	 */
	@SuppressWarnings("unchecked")
	public static Card highestLeadInHand(char trump, char lead, Card[] hand){
		if (!hasLead(lead, hand)){
			return null;
		}
		if (trump == lead){ //lead must be evaluated as trump.
			return highestCardInHand(trump, lead, hand);
		}
		LinkedList<Card> cardList = new LinkedList();
		for (Card card: hand){
			if (card != null){
				if (card.getSuit() == lead){ //bowers would have been ignored, why there is a trump == lead case.
					cardList.add(card);
				}
			}
		}
		return highestCardInHand(trump, lead, hand);
	}
	
	/**
	 * Given an array of cards(represents a hand) along with the trump and lead suits, picks the lowest trump card. Null if no trump exist.
	 * 
	 * @param trump The suit that is trump.
	 * @param lead The suit that is lead.
	 * @param hand The array of card to find the highest lead card in.
	 * @return The lowest lead car, or null if no lead cards exist.
	 */
	public static Card lowestTrumpInHand(char trump, char lead, Card[] hand){
		if (!hasTrump(trump, hand)){
			return null;
		}
		Card currentLowest = null;
		int currentLValue = 14; //every card evaluation is lower in value
		for (Card card: hand){
			if (card != null){
				if (cardValue(trump, lead, card) < currentLValue && cardValue(trump, lead, card) > 6){//6 is highest non-trump.
					currentLValue = cardValue(trump, lead, card);
					currentLowest = card;
				}
			}
		}
		return currentLowest;
	}
	
	/**
	 * Given an array of cards(represents a hand) along with the trump and lead suits, picks the lowest lead card. Null if no lead exist.
	 * 
	 * @param trump The suit that is trump.
	 * @param lead The suit that is lead.
	 * @param hand The array of card to find the highest lead card in.
	 * @return The lowest lead car, or null if no lead cards exist.
	 */
	public static Card lowestLeadInHand(char trump, char lead, Card[] hand){
		if (!hasLead(lead, hand)){
			return null;
		}
		if (trump == lead){ //lead must be evaluated as trump.
			return lowestTrumpInHand(trump, lead, hand);
		}
		Card currentLowest = null;
		int currentLValue = 7; //lowest possible trump value.
		for (Card card: hand){
			if (card != null){
				if (cardValue(trump, lead, card) < currentLValue && cardValue(trump, lead, card) > 0){//0 is highest off-suite.
					currentLValue = cardValue(trump, lead, card);
					currentLowest = card;
				}
			}
		}
		return currentLowest;
	}
	
	/**
	 * Returns the lowest card in the hand, or null if the hand is empty.
	 * 
	 * @param trump The suit that is trump.
	 * @param lead The suit that is lead.
	 * @param hand The array of card to find the highest lead card in.
	 * @return The lowest value card in hand, or null if the hand is empty.
	 */
	public static Card lowestCardInHand(char trump, char lead, Card[] hand){
		Card currentLowest = null;
		int currentLValue = 14; //higher than highest possible value.
		for (Card card: hand){
			if (card != null){
				if (cardValue(trump, lead, card) < currentLValue){
					currentLValue = cardValue(trump, lead, card);
					currentLowest = card;
				}
			}
		}
		return currentLowest;
	}
	
	/**
	 * Returns whether or not a given hand has trump cards contained, given also the suit that is trump.
	 * 
	 * @param trump The suit that is trump.
	 * @param hand The array of cards that is the hand
	 * @return True if the hand contains one or more trump cards.
	 */
	public static boolean hasTrump(char trump, Card[] hand){
		boolean foundTrump = false;
		for (Card card: hand){
			if (card != null){
				if (card.suit == trump){
					foundTrump = true;
				}
			}
		}
		return foundTrump;
	}
	
	/**
	 * Returns whether or not a given hand has lead cards contained, given also the suit that is lead.
	 * 
	 * @param lead The suit that is lead.
	 * @param hand The array of cards that is the hand
	 * @return True if the hand contains one or more lead cards.
	 */
	public static boolean hasLead(char lead, Card[] hand){
		boolean foundLead = false;
		for (Card card: hand){
			if (card != null){
				if (card.suit == lead){
					foundLead = true;
				}
			}
		}
		return foundLead;
	}
	
	/**
	 * Test cases
	 * 
	 * @param args
	 */
	/*public static void main(String[] args){
		Card[] hand = {new Card('0','h'), new Card('j','c'), new Card('j','s'), new Card('a','h'), new Card('a','d')};
		char trump = 's';
		char lead = 'h';
		System.out.println("trump = '" + trump + "'  lead = '" + lead + "'");
		System.out.print("Cards in hand: ");
		for (Card card: hand){
			System.out.print(" || card = "+card.toString()+" value = " + cardValue(trump, lead, card) + " || ");
		}
		System.out.println();
		System.out.println("hasTrump: " + hasTrump(trump, hand));
		System.out.println("hasLead: " + hasLead(lead, hand));
		System.out.println("Highest Card: " + highestCardInHand(trump, lead, hand));
		System.out.println("Highest lead: " + highestLeadInHand(trump, lead, hand));
		System.out.println("Lowest trump: " + lowestTrumpInHand(trump, lead, hand));
		System.out.println("Lowest lead: " + lowestLeadInHand(trump, lead, hand));
		System.out.println("Lowest card: " + lowestCardInHand(trump, lead, hand));
	}*/
}