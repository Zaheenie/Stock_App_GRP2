package stocks;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application{
	
	public static void main(String [] args) {
		
		launch(args);
				
	}

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StockApp Plus");
      
        try {
        	
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("ui.fxml"));
        	
        	Parent content = loader.load();
        	Scene scene = new Scene(content);
        	
        	primaryStage.setScene(scene);
            primaryStage.show();
        	
        }
        catch (Exception e) {
        	
        	e.printStackTrace();
        }       

    }

}