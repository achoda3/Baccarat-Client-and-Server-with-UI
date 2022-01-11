import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaccaratDealer{
		ArrayList<Card> deck;
		String tempSuite;
		
		public void generateDeck() {
			deck = new ArrayList<Card>();
			for(int i=0; i<4; i++) {
				if(i==0) {
					tempSuite="Hearts";
				} else if(i==1) {
					tempSuite="Spades";
				} else if(i==2) {
					tempSuite="Clubs";
				} else if(i==3) {
					tempSuite="Diamonds";
				}
				for(int j=1; j<=9; j++) {
					if(j==1) {
						deck.add(new Card(tempSuite,10));
						deck.add(new Card(tempSuite,10));
						deck.add(new Card(tempSuite,10));
						deck.add(new Card(tempSuite,10));
						deck.add(new Card(tempSuite,1));
					} else {
						deck.add(new Card(tempSuite, j));
					}
				}
			}
		}
		
		public ArrayList<Card> dealHand(){
			ArrayList<Card> hand;
			hand=new ArrayList<Card>();
			Random rand = new Random();
			int rand1;
			rand1 = rand.nextInt(deck.size());
			hand.add(deck.get(rand1));
			deck.remove(rand1);
			rand1 = rand.nextInt(deck.size());
			hand.add(deck.get(rand1));
			deck.remove(rand1);
			return hand;
		}
		
		public Card drawOne() {
			Random rand = new Random();
			int rand1;
			rand1 = rand.nextInt(deck.size());
			Card drawn = deck.get(rand1);
			deck.remove(rand1);
			return drawn;
		}
		
		public void shuffleDeck() {
			Collections.shuffle(deck);
		}
		
		public int deckSize() {
			return deck.size();
		}
		
	};