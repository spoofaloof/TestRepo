import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * <p>This Class is in charge of creating all the panes associated with the Twitter data representation.
 * It includes a user profile tab, a search tab, a messaging tab, and a preset tweets tab. 
 * The settings pane is created as well.</p>
 * @author Luis M  and Victor K
 * @category Twitter Tab Pane 
 */
public class myTabPane extends TabPane {

	ProfilePane profilePane;
	SearchPane searchPane ;
	MessagingPane msgPane ;
	SettingsPane settingsPane ;
	PresetTweetsPane presetsPane;

	//constructor
	/**
	 * <p>The gui is passed to the settings pane to allow it to switch between panes. 
	 * The user interface object is passed to the new panes in order to call Twitter related functions.</p>
	 * @param GUI object is passed in order to switch between panes
	 * @param UserInterfaceObject object is passed in order to interact with Twitter.
	 */
	public myTabPane(GUI gui, UserInterfaceObject test){

		
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		//tab creation
		Tab	profileTab = new Tab();
		Tab searchTab = new Tab();
		Tab msgTab = new Tab();
		Tab presetsTab = new Tab();

		//once logged in panes
		profilePane = new ProfilePane(test);
		searchPane = new SearchPane(test);
		msgPane = new MessagingPane(test);
		settingsPane = new SettingsPane(gui);
		presetsPane = new PresetTweetsPane(test);

		//setting tabs
		profileTab.setText("Profile Page");
		profileTab.setContent(profilePane);
		getTabs().add(profileTab);

		searchTab.setText("Search Page");
		searchTab.setContent(searchPane);
		getTabs().add(searchTab);

		msgTab.setText("Message Page");
		msgTab.setContent(msgPane);
		getTabs().add(msgTab);

		presetsTab.setText("Auto Tweet");
		presetsTab.setContent(presetsPane);
		getTabs().add(presetsTab);

	}





}
