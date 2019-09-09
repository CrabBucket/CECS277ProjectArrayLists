import java.util.ArrayList;
/**
 * <h1> A Hand that belongs to a player </h1>
 * Hand extends Deck
 * Hands have player ownership
 * Slightly Modified toString
 * 
 * @see Deck
 * @author Thomas McSwain
 * @since 2019-09-03
 * @version 1.0
 *
 */
public class Hand extends Deck{
	String player = "";
	/**
	 * Default constructor makes a Hand that belongs to player.
	 * @param player - The player the hand belongs to.
	 */
	public Hand(String player) {
		super();
		this.player = player;
	}
	
	public Hand(String player, ArrayList<Card> cards) {
		super(cards);
		this.player= player; 
	}
	/**
	 * Slightly modified toString that includes the ownership for all the cards in the hand.
	 */
	public String toString() {
		String out = "";
		for(Card c: this.cards) {
			out += "Card for " + player + " is " + c + "\n";		
		}
		return out;	
	}
	/**
	 * Sets the name of the owner of the hand.
	 * @param name The name of the hand owner.
	 */
	public void setPlayer(String name) {
		player = name;
	}
	/**
	 * Returns the name of the owner of the deck.
	 * @return Name of hand owner.
	 */
	public String getPlayer() {
		return player;
	}
}
