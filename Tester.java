
public class Tester {
	public void fight(Hand[] field, Hand[] hands) {
		if(field[0].getTop().compareTo(field[1].getTop())>0) {
			System.out.println(field[0].toString());
			System.out.println(field[1].toString());
			System.out.println(field[0].getPlayer()+ " wins this round.");
			for(Hand hand : field) {
				for(Card c: hand) {
					c.setVisible(true);
					hands[0].add(hand.removeTop());
				}
				
			}
		}else if(field[0].getTop().compareTo(field[1].getTop())<0) {
			System.out.println(field[0].toString());
			System.out.println(field[1].toString());
			System.out.println(field[1].getPlayer()+ " wins this round.");
			for(Hand hand : field) {
				for(Card c: hand) {
					c.setVisible(true);
					hands[1].add(hand.removeTop());
				}
				
			}
		}else {
			System.out.println(field[0].toString());
			System.out.println(field[1].toString());
			System.out.println("Both players have the same value card, War!");
			int playerCount = 0;
			for(Hand hand:hands) {
				for(int i = 0;i<3;i++) {
					field[playerCount].add(hand.removeTop());
					field[playerCount].getTop().setVisible(true);
					System.out.println(field[play])
				}
			}
		}
	}
	public static void main(String[] args) {
		int players = 2;
		Deck test1 = new Deck(52);
		
		test1.shuffle();
		Hand[] hands = test1.deal(players);
		Hand[] field = new Hand[players];
		int playerCount = 1;
		for(Hand hand: field) {
			hand.setPlayer("Player "+playerCount);
			playerCount++;
		}
		while(hands[0].size()>0&&hands[1].size()>0) {
			int count = 0;
			for(Hand hand: hands){
				field[0].add(hand.removeTop());
				count=((count+1)%players);
			}
			
			
		}
		

	}

}
