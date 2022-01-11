import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void CardTest() {
		Card test = new Card("Diamonds", 5);
		assertEquals(test.suite, "Diamonds");
		assertEquals(test.value, 5);
	}

	@Test
	void generateTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		
		assertEquals(test.deck.size(),52);
	}
	
	@Test
	void generateTest1() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		
		assertEquals(10,test.deck.get(0).value);
		assertEquals("Hearts",test.deck.get(0).suite);
		assertEquals(9,test.deck.get(51).value);
		assertEquals("Diamonds",test.deck.get(51).suite);
	}
	
	@Test
	void dealTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		ArrayList<Card> hand = test.dealHand();
		assertEquals(test.deck.size(),50);
		assertEquals(hand.size(),2);
		assertEquals(test.deck.contains(hand.get(0)),false);
		assertEquals(test.deck.contains(hand.get(1)),false);
	}
	
	@Test
	void dealTest1() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		ArrayList<Card> hand = test.dealHand();
		ArrayList<Card> hand1 = test.dealHand();
		assertEquals(test.deck.size(),48);
		assertEquals(hand.size(),2);
		assertEquals(hand1.size(),2);
		assertEquals(test.deck.contains(hand.get(0)),false);
		assertEquals(test.deck.contains(hand.get(1)),false);
		assertEquals(test.deck.contains(hand1.get(0)),false);
		assertEquals(test.deck.contains(hand1.get(1)),false);
		assertNotSame(hand,hand1);
	}
	
	@Test
	void shuffleTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		Card first = test.deck.get(0);
		test.shuffleDeck();
		assertFalse(first==test.deck.get(0)); //might fail 1/52 times due to how shuffling works and random chance 
		assertEquals(test.deckSize(),52);
		
	}
	
	void shuffleTest1() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		Card first = test.deck.get(0);
		test.shuffleDeck();
		test.shuffleDeck();
		assertFalse(first==test.deck.get(0)); //might fail 1/52^2 times due to how shuffling works and random chance 
		assertEquals(test.deckSize(),52);
	}
	
	@Test
	void sizeTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		assertEquals(test.deckSize(), 52);
	}
	
	@Test
	void drawOneTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		Card drawn = test.drawOne();
		assertEquals(test.deck.size(),51);
		assertEquals(test.deck.contains(drawn),false);
		}
	
	@Test
	void drawOneTest1() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		Card drawn = test.drawOne();
		Card drawn2 = test.drawOne();
     	assertEquals(test.deck.size(),50);
		assertEquals(test.deck.contains(drawn),false);
		assertEquals(test.deck.contains(drawn2),false);
		}
	
	@Test
	void handTotalTest() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 1));
		hand1.add(new Card("Diamonds", 3));
		assertEquals(BaccaratGameLogic.handTotal(hand1),4);
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 8));
		hand2.add(new Card("Diamonds", 5));
		assertEquals(BaccaratGameLogic.handTotal(hand2),3);
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand3.add(new Card("Diamonds", 9));
		hand3.add(new Card("Diamonds", 9));
		hand3.add(new Card("Diamonds", 9));
		assertEquals(BaccaratGameLogic.handTotal(hand3),7);
	}
	@Test
	void handTotalTest1() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 3));
		hand1.add(new Card("Diamonds", 3));
		assertEquals(BaccaratGameLogic.handTotal(hand1),6);
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 7));
		hand2.add(new Card("Diamonds", 5));
		assertEquals(BaccaratGameLogic.handTotal(hand2),2);
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand3.add(new Card("Diamonds", 8));
		hand3.add(new Card("Diamonds", 9));
		hand3.add(new Card("Diamonds", 7));
		assertEquals(BaccaratGameLogic.handTotal(hand3),4);
	}
	@Test
	void whoWonTest() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 1));
		hand1.add(new Card("Diamonds", 3));
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 8));
		hand2.add(new Card("Diamonds", 5));
		assertEquals("PLAYER", BaccaratGameLogic.whoWon(hand1,hand2));
		assertEquals("BANKER", BaccaratGameLogic.whoWon(hand2,hand1));
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand3.add(new Card("Diamonds", 1));
		hand3.add(new Card("Diamonds", 2));
		hand3.add(new Card("Diamonds", 1));
		assertEquals("DRAW", BaccaratGameLogic.whoWon(hand1,hand3));
	}
	
	@Test
	void whoWonTest1() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 3));
		hand1.add(new Card("Diamonds", 4));
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 2));
		hand2.add(new Card("Diamonds", 1));
		assertEquals("PLAYER", BaccaratGameLogic.whoWon(hand1,hand2));
		assertEquals("BANKER", BaccaratGameLogic.whoWon(hand2,hand1));
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand3.add(new Card("Diamonds", 2));
		hand3.add(new Card("Diamonds", 2));
		hand3.add(new Card("Diamonds", 3));
		assertEquals("DRAW", BaccaratGameLogic.whoWon(hand1,hand3));
	}
	
	@Test
	void playerDrawTest() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 1));
		hand1.add(new Card("Diamonds", 3));
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 8));
		hand2.add(new Card("Diamonds", 0));
		assertTrue(BaccaratGameLogic.evaluatePlayerDraw(hand1));
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(hand2));
	}
	
	@Test
	void playerDrawTest1() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 2));
		hand1.add(new Card("Diamonds", 3));
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 5));
		hand2.add(new Card("Diamonds", 4));
		assertTrue(BaccaratGameLogic.evaluatePlayerDraw(hand1));
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(hand2));
	}
	
	@Test
	void bankerDrawTest() {
		Card playerN = new Card("Diamonds",-1);
		Card player0 = new Card("Diamonds",0);
		Card player2 = new Card("Diamonds",2);
		Card player4 = new Card("Diamonds",4);
		Card player6 = new Card("Diamonds",6);
		Card player7 = new Card("Diamonds",7);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 1));
		hand1.add(new Card("Diamonds", 3));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand1, player0));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand1, player2));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand1, player7));
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 5));
		hand2.add(new Card("Diamonds", 0));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand2, playerN));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand2, player2));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand2, player6));
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand3.add(new Card("Diamonds", 2));
		hand3.add(new Card("Diamonds", 2));
		hand3.add(new Card("Diamonds", 2));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand3, playerN));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand3, player4));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand3, player6));
	}
	
	@Test
	void bankerDrawTest1() {
		Card playerN = new Card("Diamonds",-1);
		Card player0 = new Card("Diamonds",0);
		Card player2 = new Card("Diamonds",2);
		Card player4 = new Card("Diamonds",4);
		Card player6 = new Card("Diamonds",6);
		Card player7 = new Card("Diamonds",7);
		Card player8 = new Card("Diamonds",8);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("Diamonds", 0));
		hand1.add(new Card("Diamonds", 3));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand1, player8));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand1, player2));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand1, player7));
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("Diamonds", 5));
		hand2.add(new Card("Diamonds", 4));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand2, playerN));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand2, player2));
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(hand2, player6));
		ArrayList<Card> hand3 = new ArrayList<Card>();
		hand3.add(new Card("Diamonds", 1));
		hand3.add(new Card("Diamonds", 0));
		hand3.add(new Card("Diamonds", 0));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand3, playerN));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand3, player4));
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(hand3, player6));
	}
	
	@Test
	void evalTest() {
		BaccaratGame test = new BaccaratGame("PLAYER",10);
		test.evaluateWinnings();
		if(test.getBankerHand().size()<2||test.getBankerHand().size()>3) {
			fail();
		}
		if(test.getPlayerHand().size()<2||test.getPlayerHand().size()>3) {
			fail();
		}
		assertNotNull(test.getTotal());
		assertNotNull(test.getWon());
	}
	
	@Test
	void evalTest1() {
		BaccaratGame test = new BaccaratGame("PLAYER",100);
		double winnning = test.evaluateWinnings();
		if(test.getBankerHand().size()<2||test.getBankerHand().size()>3) {
			fail();
		}
		if(test.getPlayerHand().size()<2||test.getPlayerHand().size()>3) {
			fail();
		}
		if (winnning != 100 && winnning != -100 && winnning !=95 && winnning!=800) {
			fail();
		}
		assertNotNull(test.getTotal());
		assertNotNull(test.getWon());
	}
	
	@Test
	void infoConstTest() {
		BaccaratInfo test = new BaccaratInfo("PLAYER", 10);
		assertEquals("PLAYER",test.choice);
		assertEquals(10,test.bidAmount);
	}
	
	@Test
	void gameConstTest() {
		BaccaratGame test = new BaccaratGame("PLAYER",10);
		assertEquals(10, test.currentBet);
		assertEquals("PLAYER",test.Bet);
		assertNotNull(test.theDealer);
	}
}
