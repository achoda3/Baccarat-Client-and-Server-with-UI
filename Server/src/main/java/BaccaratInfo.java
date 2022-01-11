import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable{

	private static final long serialVersionUID = -4556106911720622350L;
	String choice;
	double bidAmount;
	ArrayList<String> bankerHand;
	ArrayList<String> playerHand;
	double totalWinnings, amountEarned;
	String whoWon;
	int clientNum;
	
	BaccaratInfo(String choice, double bidAmount){
		this.choice = choice;
		this.bidAmount = bidAmount;
	}
	
	public void setInfo(ArrayList<String> bankerHand,ArrayList<String> playerHand,
		double totalWinnings,double amountEarned, String whoWon, int clientNum){
		this.bankerHand=bankerHand;
		this.playerHand=playerHand;
		this.totalWinnings=totalWinnings;
		this.amountEarned=amountEarned;
		this.whoWon=whoWon;
		this.clientNum = clientNum;
	}
	String getChoice() {
		return choice;
	}
	double getAmount() {
		return bidAmount;
	}
	ArrayList<String> getBankerHand(){
		return bankerHand;
	}
	ArrayList<String> getPlayerHand(){
		return playerHand;
	}
	double getTotal() {
		return totalWinnings;
	}
	double getEarned() {
		return amountEarned;
	}
	String getWon() {
		return whoWon;
	}
	int getClient() {
		return clientNum;
	}
	Boolean Won() {
		if(whoWon.equals(choice)) {
			return true;
		}
		return false;
	}
}
