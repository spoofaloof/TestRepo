
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twitter4j.TwitterException;

/**
 * <p>This class handles the main visual interactions of the application.
 * It is in charge of switching between panes and creating most of the Classes which will be used.
 * It passes itself as an argument to the panes in order to let them call its functions.
 * </p>
 * @author Luis M and Victor K
 * @category GUI class
 */
public class GUI extends Application {
	
	//panes are attributes to be able to be accessed by other panes through the gui
	BorderPane masterPane;
	SelectionPane selectionPane;
	LoginPane loginPane;
	myTabPane tabPane;
	SettingsPane settingsPane;
	MainPane mainPane;
	//this is the user made API functions that work our way in accordance to our GUI
	UserInterfaceObject test;
	
	@Override
	public void start(Stage primaryStage) throws TwitterException, IOException  {
		
		//this pane will handle how to switch between panes
		masterPane = new BorderPane();
		masterPane.setCursor(Cursor.HAND);

		//create the twitter object and hand it a gui obj
		test = new UserInterfaceObject(this);

		//PANE CREATION

		mainPane = new MainPane(this);
		loginPane = new LoginPane(this);
		tabPane = new myTabPane(this,test);
		selectionPane = new SelectionPane(this);
		settingsPane = new SettingsPane(this);

		//set the masterpane in the scene
		Scene scene1 = new Scene(masterPane,1500,800);
		//masterPane will display the pane to view in the moment
		masterPane.setCenter(mainPane);

		//set in stage and title it and display
		primaryStage.setTitle("SocialMediaHub"); // Set the stage title
		primaryStage.setResizable(false);
		primaryStage.setScene(scene1); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}

	//SWITCHING SCREENS METHODS

	public void changePaneToSelectionPane() {
		
		masterPane.setCenter(selectionPane);
	}

	public void changePaneToLoginPane() {
		masterPane.setCenter(loginPane);
	}

	public void changePaneToTabPane() {
		masterPane.setCenter(tabPane);
	}

	public void changePaneToSettingsPane() {
		masterPane.setCenter(settingsPane);
	}

}
