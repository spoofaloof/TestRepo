import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * <p>This pane is in charge of displaying social media icons (currently only one).
 * It is also in charge of checking if a user has logged in. Then it will redirect accordingly.
 * If a user has logged in before and never logged off, this pane will redirect a user to the pane where
 * account interactions occur. Otherwise, it will redirect them to the appropriate social media login screen.
 * </p>
 * @author Luis M and Victor K
 * @category Selection Pane Class
 */
public class SelectionPane extends GridPane {

	/**
	 * <p>Creates a selection pane with a Twitter Icon picture. Also checks to see if a user exists
	 * by checking the existence of a specific file. Allows the icon to be clickable.
	 * If a user exists, this class is in charge of creating a session for the program to interact with twitter,
	 * adding user specific things to the profile pane, and changing to the tab pane which is where account interactions occur.
	 * If the user does not exist, the pane is in charge of adding session specific information to the login pane
	 * and switching to it.
	 * </p>
	 * @param GUI parameter is needed to be able to switch between panes
	 * @author Luis M and Victor K
	 */
	public SelectionPane(GUI gui) {
		
		//create the image from a file
		Image image = new Image("/twitterIcon.png");
		//display the picture
		ImageView twitterIconButton = new ImageView(image);
		//create a title for the page
		Text title = new Text("Selection Page");
		title.setStyle("-fx-font-size: 55");
		//if you click in the immediate whitespace around the picture, it will work
		twitterIconButton.setPickOnBounds(true);

		setHgap(250);
		setVgap(20);
		//column row
		add(twitterIconButton,1,1);
		add(title,1,0);
		//if the picture is clicked
		twitterIconButton.setOnMouseClicked(e->{
			
				gui.loginPane.getAndAddURL();//add the URL associated with this session to the login pane
				gui.changePaneToLoginPane();//change to the login pane
			
		});
	}
}
