import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * <p>This Class displays buttons that when pressed, will autotweet on the user's account what was printed on the button.</p>
 * @author Victor K
 * @category Preset Tweets Pane
 */
public class PresetTweetsPane extends GridPane {
	UserInterfaceObject test;
	public PresetTweetsPane(UserInterfaceObject tester) {
		Text autoTweets = new Text("Automatically tweet out common phrases");
		add(autoTweets,1,4);

		Button goodMorningDefault = new Button("Good morning!");
		add(goodMorningDefault,1,5);

		goodMorningDefault.setOnMouseClicked(e->{
			test.attemptToTweet("Good Morning!");
		});

		Button goodNightDefault = new Button("Good night!");
		add(goodNightDefault,2,5);

		goodNightDefault.setOnMouseClicked(e->{
			test.attemptToTweet("Good night!");
		});

		Button TGIFDefault = new Button("TGIF");
		add(TGIFDefault,3,5);

		TGIFDefault.setOnMouseClicked(e->{
			test.attemptToTweet("TGIF");
		});	
	}
}
	
