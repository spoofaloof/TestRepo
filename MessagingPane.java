import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * <p>Pane will eventually hold where messaging can take place
 * Not yet built.
 * Object exists</p>
 * @author Victor K
 * @category Messaging Pane Class
 */
public class MessagingPane extends GridPane {

	public MessagingPane(UserInterfaceObject test) {
		/*Twitter twitter = new TwitterFactory().getInstance();
		DirectMessage message;

		Button sendMsg = new Button("Send Message");
		add(sendMsg, 3,1);

		TextField userID = new TextField();
		add(userID, 1,1);

		TextField msgInput = new TextField();
		add(msgInput, 2,1);

		sendMsg.setOnMouseClicked(e->{
			try {
				message = twitter.sendDirectMessage(Long.parseLong(msgInput.getText()), userID.getText());
				System.out.println("Direct message successfully sent to " + message.getId());
				System.out.println(" details:" + message.toString());
				System.exit(0);
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to send a direct message: " + te.getMessage());
				System.exit(-1);
			}
		});*/

	}
}


