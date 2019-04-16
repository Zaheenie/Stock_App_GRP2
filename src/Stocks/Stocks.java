package Stocks;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Stocks extends Application {
	public void start(Stage primaryStage){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Main.fxml"));
			Parent content = loader.load(); 
			
			Scene scene = new Scene(content);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		launch(args);
	}

}