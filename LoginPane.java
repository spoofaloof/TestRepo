
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



/**
 * <p>This pane is in charge of displaying the login screen for Twitter.</p>
 * @author Luis M
 * @category Login Pane Class For Twitter
 */
public class LoginPane extends GridPane{
	GUI gui;
	/**
	 * <p>This pane creates Text describing everything that will be seen on the Twitter login screen.
	 * It also has functions allowing the addition of session specific URL that will allow users to permit
	 * the application to use their account.
	 * The submit button adds user specific objects to the profile pane if the user is authenticated by the
	 * Twitter API. Additionally, it would reroute the user to the tab pane which is where account interactions happen.
	 * </p>
	 * @param GUI parameter is needed to be able to switch between panes
	 */
	public LoginPane(GUI guiM){

		//make a global gui obj for this pane from the parameters
		gui=guiM;
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);

		//Add title
		Text paneTitle = new Text("Welcome to the Login Page");
		paneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		add(paneTitle, 2 , 1);

		//add login text
		Text logon = new Text("Log in with Twitter");
		add(logon, 2,2);

		//add URL prompt
		Text URL = new Text("Open the following URL and grant access to your account:");
		add(URL,2,3);

		//add a place to input the pin
		TextField inputPin = new TextField();
		inputPin.setPromptText("Enter the Pin recieved");
		add(inputPin,3,4);
		//submit pin button
		Button submit = new Button("SUBMIT");
		add(submit, 4, 4);
		//saved user file name
		TextField inputUsername = new TextField();
		inputUsername.setPromptText("Save user under this name to the file ");
		add(inputUsername,3,5);
		//Load a user
		Button loadUser = new Button ("Choose existing user");
		add(loadUser,4,5);
		//add no save name inputed warning
		Text nameWarning = new Text("Please choose a name to save user as");
		add(nameWarning,3,6);
		nameWarning.setVisible(false);
		//if loadUser was clicked
		loadUser.setOnMouseClicked(e->{
			gui.test.buildSavedUser();  //load the saved user
			gui.tabPane.profilePane.refreshScreen(); // add the user handle to the profile pane
			gui.changePaneToTabPane();//go to the tab pane which is where logged in users can interact with the app
		});
		//if submit was clicked
		submit.setOnMouseClicked(e->{
			//if the button was clicked, then
			if(!inputUsername.getText().trim().isEmpty()){
	
				if(gui.test.validateInputPin(inputPin.getText(),inputUsername.getText())) {
					nameWarning.setVisible(false);
					guiM.tabPane.profilePane.refreshScreen();
					gui.changePaneToTabPane();
					gui.test.makeTimeLine();
				}
			}else {
				nameWarning.setVisible(true);
			}
		});



	}
	/**
	 * <p>This function creates a textfield and adds it to the pane. The textfield contains
	 * a session URL is given to the user to copy.
	 * This URL will allow a user to connect their Twitter account to the application.
	 * </p>
	 * @category Twitter Session Information
	 * @see User Object Interface Class
	 */
	public void getAndAddURL() {

		TextField output = new TextField(gui.test.getAuthURL());
		output.setEditable(false);
		output.setStyle("-fx-background-color: transparent");

		add(output,2,4);
	}

}
