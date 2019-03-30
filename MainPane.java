
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * <p>This pane displays the SMH logo and allows it to reroute to the Selection Pane upon click.</p>
 * @author Victor K
 * @category Main Pane Class
 */
public class MainPane extends GridPane {

	public MainPane(GUI gui) {
		//create the image from a file
		Image image = new Image("./smh.jpg");
		
		//display the picture
		ImageView smhIconButton = new ImageView(image);
		smhIconButton.setFitWidth(900);
		smhIconButton.setFitHeight(900);
		//if you click in the immediate whitespace around the picture, it will work
		smhIconButton.setPickOnBounds(true);
		//column row
		add(smhIconButton,1,0);

		//if the picture is clicked
		smhIconButton.setOnMouseClicked(e->{
			gui.changePaneToSelectionPane();//go to the Selection Pane
		});
	}
}
