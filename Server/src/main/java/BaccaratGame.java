import java.util.ArrayList;

public class BaccaratGame extends BaccaratGameLogic{
		ArrayList<Card> playerHand;
		ArrayList<Card> bankerHand;
		BaccaratDealer theDealer;
		double currentBet;
		double totalWinnings;
		String Bet;
		String winner;
		
		BaccaratGame(String Bet, double amount){
			currentBet = amount;
			this.Bet=Bet;
			theDealer=new BaccaratDealer();
			theDealer.generateDeck();
			theDealer.shuffleDeck();
		}
		
		public double evaluateWinnings() {
			playerHand = theDealer.dealHand();
			bankerHand = theDealer.dealHand();
			if(evaluatePlayerDraw(playerHand)) {
				playerHand.add(theDealer.drawOne());
			}
			if(playerHand.size()==3) {
				if(evaluateBankerDraw(bankerHand, playerHand.get(2))) {
					bankerHand.add(theDealer.drawOne());
				}
			} else if(evaluateBankerDraw(bankerHand, new Card("", -1))) {
				bankerHand.add(theDealer.drawOne());
			}
			winner = whoWon(playerHand, bankerHand);
			if(Bet.equals(winner)) {
				if(Bet.equals("DRAW")) {
					totalWinnings=9*currentBet;
					return 8*currentBet;
				} else if(Bet.equals("PLAYER")) {
					totalWinnings=2*currentBet;
					return currentBet;
				} else {
					totalWinnings=2*currentBet-0.05*currentBet;
					return currentBet-0.05*currentBet;
				}
			} else {
				totalWinnings = 0;
				return -1*currentBet;
			}
			
			
		}
		
		ArrayList<String> getBankerHand(){
			ArrayList<String> bankerList;
			bankerList = new ArrayList<String>();
			for(int i=0; i<bankerHand.size();i++) {
				bankerList.add(((Integer)bankerHand.get(i).value).toString()+bankerHand.get(i).suite.charAt(0));
			}
			return bankerList;
		}
		
		ArrayList<String> getPlayerHand(){
			ArrayList<String> playerList;
			playerList = new ArrayList<String>();
			for(int i=0; i<playerHand.size();i++) {
				playerList.add(((Integer)playerHand.get(i).value).toString()+playerHand.get(i).suite.charAt(0));
			}
			return playerList;
		}
		
		double getTotal() {
			return totalWinnings;
		}
		String getWon() {
			return winner;
		}
	};