import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * <p>Pane will eventually hold where messaging can take place
 * Not yet built.
 * Object exists</p>
 * @author Victor K
 * @category Messaging Pane Class
 */
public class TimelinePane extends GridPane {

	UserInterfaceObject test;

	Text[] tweet= new Text[20];
	Text[] retweets=new Text[20];
	Text[]likes= new Text[20];
	Text[]screename=new Text[20];
	TextField[]tweetId=new TextField[20];
	Text[]createdAt=new Text[20];
	ScrollPane scroll = new ScrollPane();


	public TimelinePane(UserInterfaceObject tester) {
		test = tester;
		Button refresh = new Button("Refresh TimeLine");
		Button likeATweet = new Button("LIKE");
		Button rtATweet = new Button ("RETWEET");
		TextField likedTweet = new TextField();
		TextField rtedTweet = new TextField();
		Button submitComment = new Button ("COMMENT");
		TextField commentedTweet = new TextField();
		TextField comment = new TextField();
		comment.setPromptText("Type Comment Here");
		commentedTweet.setPromptText("Tweet ID to comment on");
		add(comment,5,1);
		add(submitComment,4,0);
		add(commentedTweet,4,1);
		add(rtedTweet,3,1);
		add(likedTweet,3,0);
		add(likeATweet,2,0);
		add(rtATweet,2,1);
		add(refresh,1,0);
		submitComment.setOnMouseClicked(e->{
			test.commentOnATweet(commentedTweet.getText(), comment.getText());
			refreshTimeLine();
		});
		likeATweet.setOnMouseClicked(e->{
			test.likeATweet(likedTweet.getText());
			refreshTimeLine();
		});
		rtATweet.setOnMouseClicked(e->{
			test.rtATweet(rtedTweet.getText());
			refreshTimeLine();
		});
		refresh.setOnMouseClicked(e->{
			refreshTimeLine();
		});
		scroll.setContent(this);
//		this.setVgap(100);
//		this.setHgap(100);
	}

	private void createTimeLine(){
		String [][] timeLineModel = new String [20][7];
		timeLineModel= test.getTimeLineModel();
		int i;

		for(i = 0; i < 20; i++){
				getChildren().remove(tweetId[i]);
				getChildren().remove(tweet[i]);
				getChildren().remove(screename[i]);
				getChildren().remove(likes[i]);
				getChildren().remove(retweets[i]);
				getChildren().remove(createdAt[i]);

				tweetId[i] = new TextField("Tweet ID: "+timeLineModel[i][0]); //0 , 6 ,12 , 18
				tweet[i] = new Text(timeLineModel[i][1]);//1, 7 , 13 ,19
				screename[i] = new Text("Tweeted by: @"+timeLineModel[i][3]);//2, 8, 14, 20
				likes[i] = new Text("Likes: "+timeLineModel[i][4]);//3, 9, 15, 21
				retweets[i] = new Text("Retweets: "+timeLineModel[i][5]);//4, 10, 16, 22
				createdAt[i]=new Text("Created On: "+timeLineModel[i][2]);//5, 11, 17, 23
				
				tweetId[i].setEditable(false);
				tweetId[i].setStyle("-fx-background-color: transparent");
				
				this.add(tweetId[i], 0,(i*6));
				this.add(screename[i],0,((i*6)+1));
				this.add(tweet[i], 0,((i*6)+2));
				this.add(likes[i], 0,((i*6)+3));
				this.add(retweets[i], 0,((i*6)+4));
				this.add(createdAt[i], 0,((i*6)+5));

		}

	}
	public void refreshTimeLine(){
		test.makeTimeLine();
		createTimeLine();
	}

}
