import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
	Text[] tweet= new Text[20];
	Text[] retweets=new Text[20];
	Text[]likes= new Text[20];
	Text[]screename=new Text[20];
	TextField[]tweetId=new TextField[20];
	Text[]createdAt=new Text[20];
	ScrollPane scroll = new ScrollPane();


	/**
	 * <p>This class is in charge of displaying the Twitter Profile of a user.
	 * Any user related information on the page is only added when a user begins to exist.</p>
	 * @param UserInterfaceObject object is used to call the functions related to Twitter.
	 */
	public ProfilePane(UserInterfaceObject tester) {
		Button refresh = new Button("Refresh TimeLine");
		add(refresh,7,0);
		refresh.setOnMouseClicked(e->{
			refreshMyTimeLine();
		});
		scroll.setContent(this);

		test=tester;
		setHgap(10);
		setVgap(10);
		Button likeATweet = new Button("LIKE");
		Button rtATweet = new Button ("RETWEET");
		TextField likedTweet = new TextField();
		likedTweet.setPromptText("liked tweet");
		TextField rtedTweet = new TextField();
		rtedTweet.setPromptText("retweeted tweet");
		Button submitComment = new Button ("COMMENT");
		TextField commentedTweet = new TextField();
		TextField comment = new TextField();
		comment.setPromptText("Type Comment Here");
		commentedTweet.setPromptText("Tweet ID to comment on");
		
		add(submitComment,0,12); add(commentedTweet,1,12);
		add(comment,1,13);
		
		add(rtedTweet,1,11);
		add(likedTweet,1,10);
		add(likeATweet,0,10);
		add(rtATweet,0,11);
		submitComment.setOnMouseClicked(e->{
			test.commentOnATweet(commentedTweet.getText(), comment.getText());
			refreshMyTimeLine();
		});
		likeATweet.setOnMouseClicked(e->{
			test.likeATweet(likedTweet.getText());
			refreshMyTimeLine();
		});
		rtATweet.setOnMouseClicked(e->{
			test.rtATweet(rtedTweet.getText());
			refreshMyTimeLine();
		});

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
				refreshMyTimeLine();
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
				refreshMyTimeLine();
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
				Files.deleteIfExists(Paths.get("./Saved Users/"+test.getUsername()+".txt"));
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
		name = new Text ("Name: "+test.getUserProfileName());
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
		refreshMyTimeLine();
		addUserBioToPane();
		addProfilePicToPane();
		addUserHandleToPane();
		addFriendsAndFollowers();
		addUserName();
	}
	//employ similar logic here to display timeline
	//make sure to include all variables
	private void createMyTimeLine(){
		String [][] myTimeLineModel = new String [20][7];
		myTimeLineModel= test.getMyTimeLineModel();
		int i;

		for(i = 0; i < 20; i++){
				getChildren().remove(tweetId[i]);
				getChildren().remove(tweet[i]);
				getChildren().remove(screename[i]);
				getChildren().remove(likes[i]);
				getChildren().remove(retweets[i]);
				getChildren().remove(createdAt[i]);

				tweetId[i] = new TextField("Tweet ID: "+myTimeLineModel[i][0]); //0 , 6 ,12 , 18
				tweet[i] = new Text(myTimeLineModel[i][1]);//1, 7 , 13 ,19
				screename[i] = new Text("Tweeted by: @"+myTimeLineModel[i][3]);//2, 8, 14, 20
				likes[i] = new Text("Likes: "+myTimeLineModel[i][4]);//3, 9, 15, 21
				retweets[i] = new Text("Retweets: "+myTimeLineModel[i][5]);//4, 10, 16, 22
				createdAt[i]=new Text("Created On: "+myTimeLineModel[i][2]);//5, 11, 17, 23
				
				tweetId[i].setEditable(false);
				tweetId[i].setStyle("-fx-background-color: transparent");
				
				this.add(tweetId[i], 8,(i*6));
				this.add(screename[i],8,((i*6)+1));
				this.add(tweet[i], 8,((i*6)+2));
				this.add(likes[i], 8,((i*6)+3));
				this.add(retweets[i], 8,((i*6)+4));
				this.add(createdAt[i], 8,((i*6)+5));
		}
	}

	private void refreshMyTimeLine(){
		test.makeUserTimeline();
		createMyTimeLine();
	}

}
