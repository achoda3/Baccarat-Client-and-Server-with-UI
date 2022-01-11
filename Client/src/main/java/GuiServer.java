
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class GuiServer extends Application{

	HashMap<String, Scene> sceneMap;
	Client clientConnection;
	
	TextField ipBox, portBox, ipDisp, portDisp;
	Boolean ipCheck=false;
	Boolean portCheck = false;
	Button connect;
	Button quit, start, player, banker, draw;
	TextField Bid, CurrentWinnings, CurrentWinningsDisp, bankerText, playerText, startText, blank;
	String choice;
	double bidAmount;
	ImageView card1, card2, card3, card4, card5, card6;
	TextArea Result;
	Boolean choicePicked=false;
	ArrayList<String> bankerHand;
	ArrayList<String> playerHand;
	int extra = 0;
	EventHandler<ActionEvent> extraHandler;
	BaccaratInfo info;
	String whoWon;
	double totalWinnings, winnings=0, currWinnings;
	int extraCounter=0;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("The Networked Client/Server GUI Example");

		//1st Scene objects
		//ipBox and portBox
		ipBox = new TextField();
		portBox = new TextField();
		ipDisp = new TextField("Enter ip address:");
		portDisp = new TextField("Enter port:");
		ipDisp.setEditable(false);
		portDisp.setEditable(false);
		
		ipBox.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				ipCheck = true;
			}
			if(ipCheck&&portCheck) {
				try {
					clientConnection = new Client(data->{
						Platform.runLater(()->{bankerHand = ((BaccaratInfo)data).getBankerHand();
							playerHand = ((BaccaratInfo)data).getPlayerHand();
							totalWinnings= ((BaccaratInfo)data).getTotal();
							winnings += ((BaccaratInfo)data).getEarned();
							currWinnings = ((BaccaratInfo)data).getEarned();
							whoWon=((BaccaratInfo)data).getWon();
							extra=0;
							extra+= playerHand.size()-2;
							extra+= bankerHand.size()-2;
							changeCards(bankerHand,playerHand);
										});},
							ipBox.getText(), Integer.parseInt(portBox.getText()));
							clientConnection.start();
							primaryStage.setScene(sceneMap.get("client2"));
				} catch(Exception exception) {
					System.out.println("Invalid port entered");
				}
			}
		});
		
		portBox.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				portCheck=true;
			}
			if(ipCheck&&portCheck) {
				try {
					clientConnection = new Client(data->{
						Platform.runLater(()->{bankerHand = ((BaccaratInfo)data).getBankerHand();
							playerHand = ((BaccaratInfo)data).getPlayerHand();
							totalWinnings= ((BaccaratInfo)data).getTotal();
							winnings += ((BaccaratInfo)data).getEarned();
							currWinnings = ((BaccaratInfo)data).getEarned();
							whoWon=((BaccaratInfo)data).getWon();
							extra=0;
							extra+= playerHand.size()-2;
							extra+= bankerHand.size()-2;
							changeCards(bankerHand,playerHand);
										});},
							ipBox.getText(), Integer.parseInt(portBox.getText()));
							clientConnection.start();
							primaryStage.setScene(sceneMap.get("client2"));
				} catch(Exception exception) {
					System.out.println("Invalid port entered");
				}
			}
		});
		
		//2nd Scene objects
		//connect button
		connect = new Button("CONNECT");
		connect.setOnAction(e->{
				try {
					clientConnection = new Client(data->{
						Platform.runLater(()->{bankerHand = ((BaccaratInfo)data).getBankerHand();
							playerHand = ((BaccaratInfo)data).getPlayerHand();
							totalWinnings= ((BaccaratInfo)data).getTotal();
							winnings += ((BaccaratInfo)data).getEarned();
							currWinnings = ((BaccaratInfo)data).getEarned();
							whoWon=((BaccaratInfo)data).getWon();
							extra=0;
							extra+= playerHand.size()-2;
							extra+= bankerHand.size()-2;
							changeCards(bankerHand,playerHand);
										});},
							ipBox.getText(), Integer.parseInt(portBox.getText()));
							clientConnection.start();
							primaryStage.setScene(sceneMap.get("client2"));
				} catch(Exception exception) {
					System.out.println("Invalid port entered");
				}
				
		});
		
		//quit button
		quit = new Button("QUIT");
		quit.setOnAction(e->{System.exit(0);});
		quit.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//bid text
		Bid = new TextField();
		Bid.setPromptText("Bid:");
		Bid.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		
		//player button
		player = new Button("PLAYER");
		player.setOnAction(e->{
			start.setDisable(false);
			choice = "PLAYER";
			choicePicked = true;
			player.setDisable(true);
			banker.setDisable(false);
			draw.setDisable(false);
		});
		player.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//banker button
		banker = new Button("BANKER");
		banker.setOnAction(e->{
			start.setDisable(false);
			choice = "BANKER";
			choicePicked = true;
			player.setDisable(false);
			banker.setDisable(true);
			draw.setDisable(false);
		});
		banker.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//draw button
		draw = new Button("DRAW");
		draw.setOnAction(e->{
			start.setDisable(false);
			choice = "DRAW";
			choicePicked=true;
			player.setDisable(false);
			banker.setDisable(false);
			draw.setDisable(true);
		});
		draw.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//banker text
		bankerText = new TextField("BANKER");
		bankerText.setEditable(false);
		bankerText.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//player text
		playerText = new TextField("PLAYER");
		playerText.setEditable(false);
		playerText.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//blank line
		blank = new TextField();
		blank.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//The 6 cards
		Image backCard = new Image("BackCard.png");
		card1 = new ImageView();
		card2 = new ImageView();
		card3 = new ImageView();
		card4 = new ImageView();
		card5 = new ImageView();
		card6 = new ImageView();

		card1.setImage(backCard);
		card2.setImage(backCard);
		card3.setImage(backCard);
		card4.setImage(backCard);
		card5.setImage(backCard);
		card6.setImage(backCard);
		
		card1.setFitHeight(200);
		card1.setFitWidth(200);
		card1.setPreserveRatio(true);
		
		card2.setFitHeight(200);
		card2.setFitWidth(200);
		card2.setPreserveRatio(true);
		
		card3.setFitHeight(200);
		card3.setFitWidth(200);
		card3.setPreserveRatio(true);
		
		card4.setFitHeight(200);
		card4.setFitWidth(200);
		card4.setPreserveRatio(true);
		
		card5.setFitHeight(200);
		card5.setFitWidth(200);
		card5.setPreserveRatio(true);
		
		card6.setFitHeight(200);
		card6.setFitWidth(200);
		card6.setPreserveRatio(true);
		
		//Results Box
		Result = new TextArea("RESULTS");
		Result.setStyle("-fx-control-inner-background: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		Result.setEditable(false);
		Result.setMaxSize(300, 200);
			
		//start button
		start = new Button("START");
		start.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		start.setDisable(true);
		start.setOnAction(e->{
			player.setDisable(false);
			banker.setDisable(false);
			draw.setDisable(false);
			/*ArrayList<String> bankerHand;
			bankerHand = new ArrayList<String>();
			bankerHand.add("2C");
			bankerHand.add("4S");
			ArrayList<String> playerHand;
			playerHand = new ArrayList<String>();
			playerHand.add("5D");
			playerHand.add("7H");
			changeCards(bankerHand, playerHand);*/
			card1.setImage(backCard);
			card2.setImage(backCard);
			card3.setImage(backCard);
			card4.setImage(backCard);
			card5.setImage(backCard);
			card6.setImage(backCard);
			try {
				bidAmount = Double.parseDouble(Bid.getText());
				info = new BaccaratInfo(choice, bidAmount);
				clientConnection.send(info);
			} catch (Exception exception) {
				System.out.println("There was an error!");
			}
		});
		
		//current winnings display
		CurrentWinningsDisp = new TextField("Current Winnings");
		CurrentWinningsDisp.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//current winnings
		CurrentWinnings = new TextField();
		CurrentWinnings.setStyle("-fx-background-color: black;"+
				"-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-color: #DAD91E;"+
                "-fx-font-weight: bold;"+
                "-fx-text-fill: white;"+
                "-fx-border-radius: 5;");
		
		//extraHandler
		extraHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(extra==0) {
					if(whoWon.equals(choice)) {
						Result.setText(
								"You won the bid!"+
								"\nTotal Winnings is:"+((Double)totalWinnings).toString()+
								"\nWinnings is:"+((Double)currWinnings).toString()+
								"\nThe side that drew higher: "+whoWon);
						CurrentWinnings.setText(((Double)winnings).toString());
					} else {
						Result.setText("You lost the bid"+
								"\nTotal Winnings is:"+((Double)totalWinnings).toString()+
								"\nWinnings is:"+((Double)currWinnings).toString()+
								"\nThe side that drew higher: "+whoWon);
						CurrentWinnings.setText(((Double)winnings).toString());
					}
				} else if(extra==1) {
					if(extraCounter==0) {
						if(bankerHand.size()==3) {
							card3.setImage(new Image(bankerHand.get(2)+".jpg"));
						} else {
							card6.setImage(new Image(playerHand.get(2)+".jpg"));
						}
						extraCounter++;
					} else {
						if(whoWon.equals(choice)) {
							Result.setText(
									"You won the bid!"+
									"\nTotal Winnings is:"+((Double)totalWinnings).toString()+
									"\nWinnings is:"+((Double)currWinnings).toString()+
									"\nThe side that drew higher: "+whoWon);
							CurrentWinnings.setText(((Double)winnings).toString());
						} else {
							Result.setText("You lost the bid!"+
									"\nTotal Winnings is:"+((Double)totalWinnings).toString()+
									"\nWinnings is:"+((Double)currWinnings).toString()+
									"\nThe side that drew higher: "+whoWon);
							CurrentWinnings.setText(((Double)winnings).toString());
						}
					}
				} else if(extra==2) {
					if(extraCounter==0) {
						card3.setImage(new Image(bankerHand.get(2)+".jpg"));
						extraCounter++;
					} else if(extraCounter==1) {
						card6.setImage(new Image(playerHand.get(2)+".jpg"));
						extraCounter++;
					} else {
						if(whoWon.equals(choice)) {
							Result.setText(
									"You won the bid!"+
									"\nTotal Winnings is:"+((Double)totalWinnings).toString()+
									"\nWinnings is:"+((Double)currWinnings).toString()+
									"\nThe side that drew higher: "+whoWon);
							CurrentWinnings.setText(((Double)winnings).toString());
						} else {
							Result.setText("You lost the bid!"+
									"\nTotal Winnings is:"+((Double)totalWinnings).toString()+
									"\nWinnings is:"+((Double)currWinnings).toString()+
									"\nThe side that drew higher: "+whoWon);
							CurrentWinnings.setText(((Double)winnings).toString());
						}
					}
					
				}
			}
			
		};
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("client",  createClientGui());
		sceneMap.put("client2", createClientPlay());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		primaryStage.setTitle("Semi-Chonk");
		primaryStage.setScene(sceneMap.get("client"));
		primaryStage.show();
		
	}
	
	public Scene createClientGui() {
		BackgroundImage backGround = new BackgroundImage(new Image("back.jpg",1080,720,false,false),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		HBox line1 = new HBox(50, ipDisp, ipBox);
		HBox line2 = new HBox(50, portDisp, portBox);
		VBox clientBox = new VBox(35, line1,line2, connect);
		BorderPane setScene = new BorderPane();
		setScene.setCenter(clientBox);
		clientBox.setPadding(new Insets(200,100,0,0));
		setScene.setBackground(new Background(backGround));
		return new Scene(setScene, 1080, 720);
		
	}
	
	public Scene createClientPlay() {
		BorderPane gameScene = new BorderPane();
		VBox left = new VBox(50, quit, Bid, player, banker, draw, CurrentWinningsDisp, CurrentWinnings);
		gameScene.setLeft(left);
		HBox bankerCard = new HBox(50, card1, card2, card3);
		HBox playerCard = new HBox(50, card4, card5, card6);
		VBox mid = new VBox(30, bankerText, bankerCard, blank, playerCard, playerText);
		gameScene.setCenter(mid);
		HBox startBox = new HBox(start);
		VBox right = new VBox(200, Result, startBox);
		startBox.setPadding(new Insets(0,0,0,50));
		gameScene.setRight(right);
		BackgroundImage backGround = new BackgroundImage(new Image("back2.jpg",1080,720,false,false),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		gameScene.setBackground(new Background(backGround));
		return new Scene(gameScene);
	}
	
	public void changeCards(ArrayList<String> bankerHand, ArrayList<String> playerHand) {
		String cardDet = bankerHand.get(0);
		card1.setImage(new Image(cardDet+".jpg"));
		cardDet = bankerHand.get(1);
		card2.setImage(new Image(cardDet+".jpg"));
		cardDet = playerHand.get(0);
		card4.setImage(new Image(cardDet+".jpg"));
		cardDet = playerHand.get(1);
		card5.setImage(new Image(cardDet+".jpg"));
		extraCounter=0;
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(1), extraHandler),
				new KeyFrame(Duration.millis(500))
			); 
		timeline.setCycleCount(extra+1);
		timeline.play();
	}

}
