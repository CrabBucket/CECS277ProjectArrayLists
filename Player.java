/**
 * <h1>Game Player</h1>
 * A card player who stores their own "Hand"
 * (in the case of War, a Hand consisting of their half of the game Deck), and stores their name.
 * 
 * @author Alexander Dung
 * @version 1.0
 * @since 2019-09-07
 * 
 */
public class Player {
	private Hand hand;
	private String name;
	private static int strangerCounter = 1;
	
	/**
	 * Constructor that instantiates the Player with an empty Hand and name "Stranger".
	 */
	public Player() {
		this.hand = new Hand(this.name);
		this.name = "Stranger " + strangerCounter;
		strangerCounter++;
	}
	
	/**
	 * Constructor that instantiate the Player with the argument Hand and name "Stranger".
	 * @param hand Hand to give to the constructed Player.
	 */
	public Player(Hand hand) {
		this.hand = new Hand(name, hand.asCardList());
		this.name = "Stranger " + strangerCounter;
		strangerCounter++;
	}
	
	/**
	 * Constructor that instantiates the Player with the argument Hand and name.
	 * @param hand Hand to give to the constructed Player
	 * @param name the String to assign as the Player's name
	 */
	public Player(Hand hand, String name) {
		this.hand = new Hand(name, hand.asCardList());
		this.name = name;
	}
	
	/**
	 * Returns a card drawn from the Player's Hand.
	 * @param visible whether to draw the Cards "face-up" (true) or "face-down" (false)
	 * @return the Card that is returned from the member Hand
	 */
	public Card draw(boolean visible) {
		return this.hand.removeTop(visible);
	}
	
	/**
	 * Adds the argument Hand to the member Hand using the .add method.
	 * @param newHand the Hand to add to the member Hand
	 */
	public void receiveHand(Hand newHand) {
		this.hand.add(newHand);
	}
	
	/**
	 * Accessor for the Player's name.
	 * @return the Player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Mutator for the Player's name.
	 * @param newName the String to assign as the Player's new name
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Returns the number of Cards in the member Hand.
	 * @return the number of Cards in the member Hand.
	 */
	public int getHandSize() {
		return this.hand.getSize();
	}
}
