import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;


public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	int port;
	
	Server(Consumer<Serializable> call, int port){
		this.port = port;
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
			try(ServerSocket mysocket = new ServerSocket(port);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
			BaccaratGame theGame;
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			BaccaratInfo info;
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				//updateClients("new client on server: client #"+count);
					
				 while(true) {
					    try {
					    	//Saving info from the BaccaratInfo sent from client
					    	info = (BaccaratInfo)in.readObject();
					    	String choice = info.getChoice();
					    	double bid = info.getAmount();
					    	
					    	//Passing info into Game
					    	theGame=new BaccaratGame(choice, bid);
					    	
					    	//Retrieving info from Game
					    	ArrayList<String> playerList, bankerList;
					    	String whoWon;
					    	double totalWinnings, amountEarned;
					    	amountEarned = theGame.evaluateWinnings();
					    	playerList = theGame.getPlayerHand();
					    	bankerList = theGame.getBankerHand();
					    	whoWon = theGame.getWon();
					    	totalWinnings = theGame.getTotal();
					    	info.setInfo(bankerList, playerList, totalWinnings, amountEarned, whoWon, count);
					    	
					    	//Writing back to Client
					    	this.out.writeObject(info);
					    	
					    	//Writing back to GUIServer.java of server
					    	callback.accept(info);
					    	
					    	}
					    catch(Exception e) {
					    	System.out.println(e);
					    	callback.accept("The client " + count + " has been disconnected!");
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
		
}


	
	

	
