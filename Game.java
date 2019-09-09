/**
 * <h1>War Game</h1>
 * Contains the players and rules necessary to simulate the card game War.
 * 
 * @author Alexander Dung
 * @version 1.0
 * @since 2019-09-08
 * 
 */
public class Game {
	private Player player1, player2;
	private Deck gameDeck;
	private Hand warPile;
	
	/**
	 * Constructor without arguments. 
	 * Instantiates a standard Deck of Cards (52 Cards / 4 suits / 13 face values).
	 * Players have no name, and are named "Strangers", and are given a half of the shuffled Deck.
	 */
	public Game() {
		this.warPile = new Hand("War Pile");
		this.gameDeck = new Deck(52);
		this.gameDeck.shuffle();
		Hand[] hands = gameDeck.deal(2);
		this.player1 = new Player(hands[0]);
		this.player2 = new Player(hands[1]);
	}
	
	/**
	 * Constructor that instantiates a Deck of Cards with number of Cards deckSize (deckSize Cards / 4 suits / 13 face values).
	 * Players have no name, and are named "Strangers", and are given a half of the shuffled Deck.
	 * @param deckSize the number of Cards to instantiate the Deck with.
	 */
	public Game(int deckSize) {
		this.gameDeck = new Deck(deckSize);
		this.gameDeck.shuffle();
		Hand[] hands = gameDeck.deal(2);
		this.player1 = new Player(hands[0]);
		this.player2 = new Player(hands[1]);
	}
	
	/**
	 * Constructor that instantiates a Deck of Cards with number of Cards deckSize (deckSize Cards / 4 suits / 13 face values).
	 * Players 1 and 2 are instantiated with names player1Name and player2Name, respectively.
	 * @param player1Name the name to instantiate player 1 with
	 * @param player2Name the name to instantiate player 2 with
	 * @param deckSize the number of Cards to instantiate the Deck with
	 */
	public Game(String player1Name, String player2Name, int deckSize) {
		this.gameDeck = new Deck(deckSize);
		this.gameDeck.shuffle();
		Hand[] hands = gameDeck.deal(2);
		this.player1 = new Player(hands[0], player1Name);
		this.player2 = new Player(hands[1], player2Name);
	}
	
	/**
	 * Begins simulating a game of War using its member Player and Deck classes.
	 * <p>
	 * @link {takeTurn()}
	 * Players each draw a Card face-up.
	 * The player who draws the Card of higher value puts both Players' cards in their Deck.
	 * If both cards are of the same face value, War begins.
	 * <p>
	 * @link {wageWar()}
	 * During War, both Players draw 3 Cards face-down and a fourth Card face-up.
	 * The Player whose face-up Card has greater value wins all 8 Cards.
	 * If both Players' Cards are equal, repeat the previous two steps, adding all drawn cards to the 'pile' until a Player wins the comparison.
	 * The winner of the War gains all Cards played in the War.
	 * <p>
	 * War continues until a Player no longer has any Cards in their Deck.
	 * The Player who has all Cards in their Deck is the winner.
	 * <p>
	 */
	public void playGame() {
		boolean gameIsComplete = false;
		while(!gameIsComplete) {
			takeTurn();
			if(evaluateGameState() != 0) gameIsComplete = true;
		}
		if(evaluateGameState() == 1) System.out.println(player1.getName() + " has all the cards, and therefore has won the game.");
		else System.out.println(player2.getName() + " has all the cards, and therefore has won the game.");
		System.out.println(player1.getHandSize());
		System.out.println(player2.getHandSize());
	}
	
	/**
	 * Simulates taking a turn in the game War.
	 * <p>
	 * Each Player draws a Card from their respective Hand face-up.
	 * Then, compare the face values the Players' Cards.
	 * The Player whose card has a greater face value wins both played Cards.
	 * Values compare (left-most is lowest value; right-most is greatest value): 
	 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
	 * <p>
	 * If Cards of are equal value, Players enter War state: {@link wageWar()}
	 */
	public void takeTurn() {
		warPile.empty();
		System.out.println(player1.getName() + " hand size: " + player1.getHandSize() + "\n" + player2.getName() + " hand size: " + player2.getHandSize());
		if(player1.getHandSize() == 0 || player2.getHandSize() == 0) return;
		Card card1 = player1.draw(true);
		Card card2 = player2.draw(true);
		warPile.add(card1);
		warPile.add(card2);
		System.out.println(player1.getName() + " plays the " + card1 + ".");
		System.out.println(player2.getName() + " plays the " + card2 + ".");
		int warResults = card1.compareTo(card2);
		if(warResults == 1) {
			System.out.println(player1.getName() + " wins the round.");
			player1.receiveHand(warPile);
		}
		else if(warResults == -1) {
			System.out.println(player2.getName() + " wins the round.");
			player2.receiveHand(warPile);
		}
		else {
			System.out.println("Cards are of equal value. War has begun.");
			if(wageWar()) {
				System.out.println(player1.getName() + " has won the war.");
				player1.receiveHand(warPile);
			}
			else {
				System.out.println(player2.getName() + " has won the war.");
				player2.receiveHand(warPile);
			}
		}
	}
	
	/**
	 * Simulates the War phase of the War card game.
	 * <p>
	 * Both Players draw 3 Cards face-down and place them in the "War pile".
	 * Then, both Players draw a face-up Card and compare their face values.
	 * The winner wins both the played Cards and the War pile.
	 * Otherwise, if the cards are equal, place them in the War pile and repeat the previous steps.
	 * @return a boolean representing which Player won the War, where true represents player1 and false represents player2
	 */
	public boolean wageWar() {
		if(player1.getHandSize() < 4)return false;
		else if(player2.getHandSize() < 4)return true;
		for(int counter = 0; counter < 2; counter++) {
			Card warCard1 = player1.draw(false);
			this.warPile.add(warCard1);
			System.out.println(player1.getName() + " draws a war card: " + warCard1);
			Card warCard2 = player2.draw(false);
			this.warPile.add(warCard2);
			System.out.println(player2.getName() + " draws a war card: " + warCard2);
		}
		Card warDecider1 = player1.draw(true);
		Card warDecider2 = player2.draw(true);
		warPile.add(warDecider1);
		warPile.add(warDecider2);
		int warDecision = warDecider1.compareTo(warDecider2);
		if(warDecision == 1) {
			return true;
		}
		else if(warDecision == -1) {
			return false;
		}
		else return wageWar();
	}
	
	/**
	 * Evaluates whether the Game has been won and returns an integer representing the state of the game.
	 * @return an integer representing the Game's state, where 1 means player 1 won, -1 means player 2 won, and 0 means the game is ongoing
	 */
	public int evaluateGameState() {
		if(player1.getHandSize() == 0) return 1;
		else if(player2.getHandSize() == 0) return -1;
		else return 0;
	}
}
