import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/**
 * <h1>Deck of Cards</h1>
 * An object that contains an ArrayList of Card objects.
 * Contains associated methods for dealing, adding, removing, and shuffling cards.
 * 
 * @author Thomas
 * @version 1.0
 * @since 2019-09-03
 * 
 *
 */
public class Deck implements Iterable<Card>{
	ArrayList<Card> cards;
	/**
	 * Constructor that instantiates the cards ArrayList with no cards.
	 */
	public Deck() {
		this.cards = new ArrayList<Card>();
		
	}
	/**
	 * Constructor that fills the card ArrayList with cards starting from 2 to Ace for values.
	 * The order of the Suits is as follows: Spades -/> Clubs -/> Diamonds -/> Hearts.
	 * If given a number less than 52 it will fill the list with cards in order to wherever it ends.
	 * If given a number greater than 52 it will loop back around to Spades and continue to fill the deck.
	 * @param numCards Number of cards you wish to populate the deck with.
	 */
	public Deck(int numCards) {
		this.cards = new ArrayList<Card>();
		for(int i = 0; i < numCards; i++) {
			if(i%13 < 9) {
				try {
					cards.add(new Card((i%13+2)+"",suitConvert((i/13)%4)));
				} catch (InvalidConvertException e) {
					System.out.println("Threw invalid convert exception, the suit conversion failed in deck constructor.");
				}
			}else {
				try {
					cards.add(new Card(faceConvert(i%13),suitConvert((i/13)%4)));
				} catch (InvalidConvertException e) {
					System.out.println(e.toString());
					System.out.println("Thrown when trying to add face card in deck constructor.");
				}
			}
		}
	}
	/**
	 * Instantiates cards as an ArrayList of cards that is equal to whatever ArrayList of cards you pass in as a paramter.
	 * @param cards Card ArrayList you wish to create the deck with.
	 */
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
	/**
	 * "Deals" hands out to players in the form of alternating between the number of players specified.
	 * Returns an array of the Hand objects
	 * @param players Number of players
	 * @return An array contain all the players hands.
	 */
	public Hand[] deal(int players) {
		Hand[] hands = new Hand[players];
		for(int i = 0; i < hands.length; i++) {
			hands[i] = new Hand("Player"+(i+1));
		}
		int count = 0;
		while(size()>0) {
			hands[count].add(this.removeTop());
			count++;
			count=count%players;
		}
		return hands;
	}
	/**
	 * Adds a card object to the deck.
	 * @param card Card
	 */
	public void add(Card card) {
		this.cards.add(card);
	}
	/**
	 * Removes and returns the card off the top, top is at index 0.
	 * @return Returns the card removed off the top.
	 */
	public Card removeTop() {
		return cards.remove(0);
	}
	/**
	 * Removes a card at the specified index returning that card.
	 * @param index The index at which you want to remove the card.
	 * @return The card removed.
	 */
	public Card remove(int index) {
		return cards.remove(index);
	}
	/**
	 * Returns the top card of the deck.
	 * @return The top of the deck.
	 */
	public Card getTop() {
		return cards.get(0);
	}
	/**
	 * Returns the size of the deck.
	 * @return The size of the deck.
	 */
	public int size() {
		return cards.size();
	}
	/**
	 * Uses the collections shuffle method on our ArrayList.
	 * Hopefully randomizes it.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	/**
	 * Converts an integer from 0-3 into it's corresponding suit string.
	 * @param a Integer from 0-3
	 * @return Suit as a string.
	 * @throws InvalidConvertException Throws exception when a number not between 0-3 is called
	 */
	public String suitConvert(int a) throws InvalidConvertException{
		switch(a) {
			case 0:
				return "Spades";
			case 1:
				return "Clubs";
			case 2:
				return "Diamonds";
			case 3:
				return "Hearts";
			default:
				throw new InvalidConvertException("Invalid integer, must be between 0-3");
		}		
	}
	/**
	 * Converts an integer between 9-12 into the corresponding String Face card
	 * @param a The integer to be converted
	 * @return Returns the face String
	 * @throws InvalidConvertException Throws exception when number is out of the range from 10-12
	 */
	public String faceConvert(int a) throws InvalidConvertException{
		switch(a) {
			case 9:
				return "Jack";
			case 10:
				return "Queen";
			case 11:
				return "King";
			case 12:
				return "Ace";
			default:
				throw new InvalidConvertException("Invalid face integer, must be between 10-13");
		}
	}
	@Override
	public Iterator<Card> iterator() {
		
		return cards.iterator();
	}
}
