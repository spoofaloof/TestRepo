
import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import twitter4j.TwitterException;

/**
 * <p>Displays textfields to change Bio and/or name if typed in and submitted. 
 * Contains the button to submit the account changes to Twitter.
 * Contains button to go back to the profile page.
 * Contains button to change profile picture.</p>
 * @author Victor K and Luis M
 * @category Settings Pane Class
 */
public class SettingsPane extends GridPane{

	Button backButton = new Button ("Back to profile page");
	Button changePFP = new Button("Change Profile Picture");
	Button commitProfileChanges = new Button("Commit Profile Settings Changes");
	
	TextField changeBio = new TextField();
	TextField name = new TextField();
	
	FileChooser fc = new FileChooser();

	public SettingsPane(GUI gui) {
		
		name.setPromptText("NEW NAME");
		changeBio.setPromptText("NEW BIO");
				
		add(changeBio,1,2);
		add(name,1,3);
		add(backButton,0,0);
		add(changePFP,0,3);
		add(commitProfileChanges,1,1);
		
		commitProfileChanges.setOnMouseClicked(e->{
			gui.test.updateProfile(name.getText(), changeBio.getText());
			name.clear();
			changeBio.clear();
		});
	
		backButton.setOnMouseClicked(e->{
			gui.tabPane.profilePane.refreshScreen();
			gui.changePaneToTabPane();
		});

		changePFP.setOnMouseClicked(e->{

			fc.setTitle("Open Resource File");
			fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
			File selectedFile = fc.showOpenDialog(new Stage());
			if (selectedFile != null) {
				try {
						gui.test.twitter.updateProfileImage(selectedFile);
				} catch (TwitterException e1) {
					//e1.printStackTrace();
				}
			}
		});

	}

}
