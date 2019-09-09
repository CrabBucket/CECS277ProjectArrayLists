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
 */
public class Deck implements Iterable<Card>{
	protected ArrayList<Card> cards;
	
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
	 * Instantiates cards as an ArrayList of cards that is equal to the argument ArrayList.
	 * @param cards ArrayList of Cards you wish to create the Deck with.
	 */
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * "Deals, or hands out to players in the form of alternating between the number of players specified.
	 * Returns an array of Hand objects.
	 * @param players Number of Players to deal Hands to.
	 * @return An array containing Hands for each player.
	 */
	public Hand[] deal(int players) {
		Hand[] hands = new Hand[players];
		for(int i = 0; i < hands.length; i++) {
			hands[i] = new Hand("Player"+(i+1));
		}
		int count = 0;
		while(getSize()>0) {
			hands[count].add(this.removeTop(true));
			count++;
			count=count%players;
		}
		return hands;
	}
	
	/**
	 * Adds the argument Card to the deck.
	 * @param card Card to add to deck.
	 */
	public void add(Card card) {
		this.cards.add(card);
	}
	
	/**
	 * Adds Cards from the argument Deck to own deck.
	 * @param deck Deck with Cards to add to the deck.
	 */
	public void add(Deck deck) {
		this.cards.addAll(deck.asCardList());
	}
	
	/**
	 * Removes and returns the Card off the top, where the top is at index 0.
	 * If the argument is true, returns the card visible.
	 * If false, returns the card invisible.
	 * @param visible Boolean deciding whether to return the card visible.
	 * @return Returns the Card removed off the top.
	 */
	public Card removeTop(boolean visible) {
		Card drawnCard = cards.remove(0);
		if(visible) {
			drawnCard.setVisible(true);
			return drawnCard;
		}
		else {
			drawnCard.setVisible(false);
			return drawnCard;
		}
	}
	
	/**
	 * Removes a Card at the specified index, then returns that card.
	 * @param index The index at which you want to remove a Card.
	 * @return The Card removed.
	 */
	public Card remove(int index) {
		return cards.remove(index);
	}
	
	/**
	 * Clears all Cards in the ArrayList using the clear() method.
	 */
	public void empty() {
		this.cards.clear();
	}
	
	/**
	 * Returns the top Card of the deck.
	 * @return the Card on the top of the deck (index 0)
	 */
	public Card getTop() {
		return cards.get(0);
	}
	
	/**
	 * Returns the number of Cards currently in the Deck.
	 * @return the size of the Deck
	 */
	public int getSize() {
		return cards.size();
	}
	
	/**
	 * Uses the Collections utility class shuffle() method on the ArrayList of Cards.
	 * Theoretically randomizes the ArrayList
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Accessor method for this Deck's ArraList of Cards.
	 * @return An ArrayList of this Deck's Cards, in the same order.
	 */
	public ArrayList<Card> asCardList(){
		return this.cards;
	}
	
	/**
	 * Converts an integer from 0-3 into it's corresponding suit string.
	 * @param an Integer from 0-3
	 * @return the suit as a String
	 * @throws InvalidConvertException if a number not between 0-3 is called
	 */
	private String suitConvert(int a) throws InvalidConvertException{
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
	 * @param the integer to be converted
	 * @return the face as a String
	 * @throws InvalidConvertException when number is out of the range of 10-12
	 */
	private String faceConvert(int a) throws InvalidConvertException{
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
	
	/*
	public static void main(String[] args) {
		Deck deck1 = new Deck(2);
		ArrayList<Card> listo = new ArrayList<Card>();
		listo.add(new Card("King", "Hearts"));
		listo.add(new Card("Queen", "Hearts"));
		Deck deck2 = new Deck(listo);
		deck1.add(deck2);
		System.out.println(deck1.asCardList());
	}
	*/
}
