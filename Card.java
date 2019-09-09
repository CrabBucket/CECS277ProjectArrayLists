/**
 * <h1>Standard Playing Card</h1>
 * A playing card that stores a card's value, suit, and if it's visible.
 * Has Associated accessor and mutator methods and toString.
 * @author Thomas McSwain
 * @version 1.0
 * @since 2019-09-03
 *
 */
public class Card {
	private String value;
	private String suit;
	private boolean visible;
	/**
	 * Only constructor available. You must supply the value and suit of the card.
	 * @param value A String that contains the value/number/face of the card.
	 * @param suit A string that contains the suit of the card
	 */
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
		this.visible = true;
	}
	/**
	 * Compares the card that the method is called on to another Card c.
	 * If this card's value is greater than the other card c, returns 1.
	 * If this card's value is less than the other card c, returns -1.
	 * If the two cards are equal returns 0.
	 * @param c Card that you wish to compare.
	 * @return 1,-1 or 0 depending if this card is greater than, less than or equal to the other card.
	 */
	public int compareTo(Card c) {
		int result;
		if(this.faceValue() > c.faceValue()) {
			result = 1;
		}else if(this.faceValue() < c.faceValue()) {
			result = -1;
		}else {
			result = 0;
		}
		return result;
	}
	/**
	 * Sets whether the card is visible or not.
	 * @param visibility - Boolean if true card is visible
	 */
	public void setVisible(boolean visibility) {
		this.visible = visibility;
	}
	/**
	 * Returns the visibility of the card
	 * @return Boolean if true card is visible.
	 */
	public boolean getVisible() {
		return this.visible;
	}
	/**
	 * Returns string of the value.
	 * @return - String of the value.
	 */
	public String getValue() {
		return this.value;
	}
	/**
	 * Returns string of the suit
	 * @return - String that contains the suit.
	 */
	public String getSuit(){
		return this.suit;
	}
	/**
	 * Returns a formatted string that contains card value and suit.
	 * @return Formatted string that contains card value and suit.
	 */
	public String toString() {
		if(this.visible)return this.value + " of " + this.suit;
		else return "Unknown card.";
	}
	/**
	 * Returns the value of the face card if a face or returns the value of the number on the card as an Integer.
	 * @return An Integer that ranks the cards numerically.
	 */
	public int faceValue() {
		switch(this.value) {
			case "Jack":
				return 9;
			case "Queen":
				return 10;
			case "King":
				return 11;
			case "Ace":
				return 12;
			default:
				return Integer.parseInt(this.value);
		}
	}
}
