
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

	
	HashMap<String, Scene> sceneMap;
	Server serverConnection;
	Button powerButton;
	TextField port;
	int portNum;
	int clientCount=0;
	
	ListView<String> listItems;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Council of the Chonks");
		
		//List for Server Details
		listItems = new ListView<String>();
		
		//Power Button Image
		Image power = new Image("power1.png");
		ImageView powerView = new ImageView(power);
		powerView.setFitHeight(80);
		powerView.setPreserveRatio(true);
		
		//Power Button
		powerButton = new Button();
		powerButton.setGraphic(powerView);
		powerButton.setStyle(
				"-fx-background-color: black");
		//Starting the server
		powerButton.setOnAction(e->{ 
			portNum = Integer.parseInt(port.getText());
			primaryStage.setScene(sceneMap.get("server"));
			serverConnection = new Server(data -> {
				Platform.runLater(()->{
					if(data instanceof String) {
						if(data.toString().charAt(0)=='c') {
							clientCount++;
							listItems.getItems().add(data.toString());
						} else {
							clientCount--;
							listItems.getItems().add(data.toString());
						}
						listItems.getItems().add("Number of clients: "+clientCount);
					} else {
						listItems.getItems().add("---------------------------------------------");
						listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" played the game");
						listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" bet " + 
								+((BaccaratInfo)data).getAmount()+" on "+((BaccaratInfo)data).getChoice());
						listItems.getItems().add(((BaccaratInfo)data).getWon()+" had the greater hand");
						if(((BaccaratInfo)data).Won()) {
							listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" won the Bid and earned "
						+((BaccaratInfo)data).getEarned());
						} else {
							listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" lost the Bid and earned "
									+((BaccaratInfo)data).getEarned());
						}
						listItems.getItems().add("---------------------------------------------");
					}
				});},
			portNum);
		});
		
		//Port TextField
		port = new TextField();
		port.setPromptText("Enter port");
		//Alternate Start Server
		port.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				portNum = Integer.parseInt(port.getText());
				primaryStage.setScene(sceneMap.get("server"));
				serverConnection = new Server(data -> {
					Platform.runLater(()->{
						if(data instanceof String) {
							if(data.toString().charAt(0)=='c') {
								clientCount++;
								listItems.getItems().add(data.toString());
							} else {
								clientCount--;
								listItems.getItems().add(data.toString());
							}
							listItems.getItems().add("Number of clients: "+clientCount);
						} else {
							listItems.getItems().add("---------------------------------------------");
							listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" played the game");
							listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" bet " + 
									+((BaccaratInfo)data).getAmount()+" on "+((BaccaratInfo)data).getChoice());
							listItems.getItems().add(((BaccaratInfo)data).getWon()+" had the greater hand");
							if(((BaccaratInfo)data).Won()) {
								listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" won the Bid and earned "
							+((BaccaratInfo)data).getEarned());
							} else {
								listItems.getItems().add("Client "+ ((BaccaratInfo)data).getClient()+" lost the Bid and earned "
										+((BaccaratInfo)data).getEarned());
							}
							listItems.getItems().add("---------------------------------------------");
						}
					});},
				portNum);
			}
		});
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("intro",  createIntroGui());
		sceneMap.put("server",  createServerGui());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(sceneMap.get("intro"));
		primaryStage.show();
		
	}
	
	//Intro GUI
	public Scene createIntroGui() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		BackgroundImage backGround = new BackgroundImage(new Image("back.jpg",1080,720,false,false),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		pane.setBackground(new Background(backGround));
		VBox left = new VBox(200,powerButton,port);
		pane.setLeft(left);
		return new Scene(pane, 1080, 720);
	}
	
	//Server GUI
	public Scene createServerGui() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		BackgroundImage backGround = new BackgroundImage(new Image("border1.jpg",1080,720,false,false),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		pane.setBackground(new Background(backGround));
		pane.setCenter(listItems);
		return new Scene(pane, 1080, 720);
	}

}
