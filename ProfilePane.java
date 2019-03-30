import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * <p>The pane in its entirety displays a user's name, Twitter handle, bio, profile picture, and how many people the user follows and is following.
 * The pane also has a button that allows the user to tweet on their profile whatever is filled into the textfield below the button.
 * The pane also includes a second button which has the same functionality as the previous button but also automatically tags Wendy's in the tweet.
 * The pane also contains a button to logout of Twitter. </p>
 * @author Luis M and Victor K and Victoria J
 * @category Twitter Profile Pane
 */
public class ProfilePane extends GridPane{
	UserInterfaceObject test;
	Circle circle1;
	Button changePaneToSettingsPane;
	Text name, userHandle, status, followers, following, tooLong ;


	/**
	 * <p>This class is in charge of displaying the Twitter Profile of a user.
	 * Any user related information on the page is only added when a user begins to exist.</p>
	 * @param UserInterfaceObject object is used to call the functions related to Twitter.
	 */
	public ProfilePane(UserInterfaceObject tester) {

		test=tester;
		setHgap(10);
		setVgap(10);

		Button tweetOut = new Button("Tweet Out!!");
		add(tweetOut, 0,3);

		tooLong = new Text("Too many characters");
		add(tooLong,0,6);
		tooLong.setVisible(false);

		TextField tweet = new TextField();

		this.add(tweet, 0,4);


		tweetOut.setOnMouseClicked(e->{
			if(tweet.getLength() <= 280) {
				test.attemptToTweet(tweet.getText());
				tooLong.setVisible(false);
			}else {
				tooLong.setVisible(true);
			}
		});

		Button wendysDefault = new Button("Tweet @Wendys");
		add(wendysDefault,0,5);
		wendysDefault.setOnMouseClicked(e->{
			if(tweet.getLength() <= 272) {
				test.attemptToTweet(tweet.getText() + " @Wendys");
				tooLong.setVisible(false);
			}else {
				tooLong.setVisible(true);
			}

		});

		changePaneToSettingsPane = new Button("Settings Pane");
	    add(changePaneToSettingsPane, 6, 2);

	    circle1 = new Circle(20,20,20);
	    circle1.centerXProperty().bind(widthProperty().divide(4));
	    circle1.centerYProperty().bind(heightProperty().divide(4));
	    circle1.setRadius(100);
	    circle1.setStroke(Color.AQUA);
	    circle1.setStrokeWidth(3);

	    add(circle1,0,0);

		Button logOut = new Button("Log Out of Twitter");
		add(logOut,5,0);

		logOut.setOnMouseClicked(e->{
			try {
				Files.deleteIfExists(Paths.get("./userKeys.txt"));
			}
			catch(NoSuchFileException nf){
				nf.printStackTrace();
			}
			catch(DirectoryNotEmptyException dne) {
				dne.printStackTrace();
			}
			catch(IOException io) {
				io.printStackTrace();
			}
			System.exit(0);
		});

		changePaneToSettingsPane.setOnMouseClicked(e->{
			test.guiObject.changePaneToSettingsPane();
		});
	}
	/**
	 * <p>This function adds a user's bio to the pane in the form of text.
	 * The function depends on the fact that a user has to have been created
	 * by the program in order to use the function. </p>
	 */
	private void addUserBioToPane (){
		getChildren().remove(status);
		status = new Text("Bio: "+test.getBioString());
		add(status,1,1);
	}
	/**
	 * <p>This function adds a user's profile picture to the pane.
	 * The function depends on the fact that a user has to have been created
	 * by the program in order to use the function. </p>
	 */
	private void addProfilePicToPane() {
		Image image = new Image(test.getURLProfilePic());
		circle1.setFill(new ImagePattern(image));
	}
	/**
	 * <p>This function adds a user's Twitter handle to the pane in the form of text.
	 * The function depends on the fact that a user has to have been created
	 * by the program in order to use the function. </p>
	 */
	private void addUserHandleToPane() {
		getChildren().remove(userHandle);
		userHandle = new Text("User: @"+test.getUserHandle());
		add(userHandle,0,2);
	}
	/**
	 * <p>This function adds a user's Twitter name to the pane in the form of text.
	 * The function depends on the fact that a user has to have been created
	 * by the program in order to use the function. </p>
	 */
	private void addUserName() {
		getChildren().remove(name);
		name = new Text ("Name: "+test.getUserName());
		add(name,0,1);

	}
	/**
	 * <p>
	 * This function adds a user's friends and followers to the pane in the form of text.
	 * The function depends on the fact that a user has to have been created
	 * by the program in order to use the function. </p>
	 */
	private void addFriendsAndFollowers() {
		getChildren().remove(followers);
		getChildren().remove(following);
		followers = new Text("Followers: "+test.getFollowersCount());
		following = new Text("Following: "+test.getFollowingCount());

	   add(followers, 1, 2);
	   add(following, 2, 2);
	}

	/**
	 * <p>This function adds all user specific data to the pane when called upon
	 * Includes: Bio, Profile Picture, User Handle, User Name, Friends and Followers count</p>
	 */
	public void refreshScreen(){
		tooLong.setVisible(false);
		addUserBioToPane();
		addProfilePicToPane();
		addUserHandleToPane();
		addFriendsAndFollowers();
		addUserName();
	}

}
