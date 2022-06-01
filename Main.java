package application;

import javafx.application.Application;
import javafx.fxml.*;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
public class Main extends Application {
	public void start(Stage primaryStage) throws Exception {
			
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));	
			root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			primaryStage.setScene(new Scene(root));
			//added resizable false
			primaryStage.setResizable(false);
			primaryStage.setTitle("From Java To C# In A Flash, Monkey See, Monkey Do!");
			Image icon = new Image(getClass().getResourceAsStream("Java2C#Logo.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
	
	}
	
	public static void main(String[] args) {
		 launch(args);
	}
}