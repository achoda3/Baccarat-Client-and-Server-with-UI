import java.util.ArrayList;

public class BaccaratGameLogic{
		
		public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
			int sum=handTotal(hand);
			if(sum<6) {
				return true;
			}
			return false;
		}
		
		public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
			int sum=handTotal(hand);
			if(sum>7) {
				return false;
			}
			if(sum<3) {
				return true;
			}
			if(playerCard.value==-1) {
				if(sum<6) {
					return true;
				} 
				return false;
			}
			if(playerCard.value==0) {
				if(sum==3) {
					return true;
				}
				return false;
			}
			if(playerCard.value==1) {
				if(sum==3) {
					return true;
				}
				return false;
			}
			if(playerCard.value==2) {
				if(sum<5) {
					return true;
				}
				return false;
			}
			if(playerCard.value==3) {
				if(sum<5) {
					return true;
				}
				return false;
			}
			if(playerCard.value==4) {
				if(sum<6) {
					return true;
				}
				return false;
			}
			if(playerCard.value==5) {
				if(sum<6) {
					return true;
				}
				return false;
			}
			if(playerCard.value==6) {
				if(sum<7) {
					return true;
				}
				return false;
			}
			if(playerCard.value==7) {
				if(sum<7) {
					return true;
				}
				return false;
			}
			if(playerCard.value==8) {
				return false;
			}
			if(playerCard.value==9) {
				if(sum==3) {
					return true;
				}
				return false;
			}
			return false;
		}
		
		public static int handTotal(ArrayList<Card> hand) {
			int sum=0;
			for(int i=0; i<hand.size(); i++) {
				sum+=hand.get(i).value;
			}
			while(sum>=10) {
				sum=sum-10;
			}
			return sum;
		}

		public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
			int playerHand = handTotal(hand1);
			int bankerHand = handTotal(hand2);
			if(playerHand==bankerHand) {
				return "DRAW";
			} else if(playerHand>bankerHand) {
				return "PLAYER";
			} else {
				return "BANKER";
			}
		}
		
	};