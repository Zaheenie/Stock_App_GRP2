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

/*	
	public GridPane displayStockInfo(String symbol) {
		
		StockDownloader currentStock = new StockDownloader(symbol);
		NameSearchDownloader currentStockPlus = new NameSearchDownloader(symbol);
		
		final int FONTSIZE1 = 20;
		
		GridPane display = new GridPane();
		
		display.setHgap(10);
	    display.setVgap(10);
	    display.setPadding(new Insets(0, 10, 0, 10));
	    
	    // *** Text ***     Displays Information about the company	    
	    Text name = new Text(currentStockPlus.getName()+" (" + currentStockPlus.getSymbol() + ")");
	    name.setFont(Font.font("Arial", FONTSIZE1 ));
	    display.add(name, 15, 6);
	    
	    Text region = new Text(currentStockPlus.getRegion());
	    region.setFont(Font.font("Arial", FONTSIZE1 ));
	    display.add(region, 15, 7);
	    
	    Text currency = new Text("Currency in " + currentStockPlus.getCurrency());
	    currency.setFont(Font.font("Arial", FONTSIZE1 ));
	    display.add(currency, 15, 8);
		
	    
	    // *** Text ***		Retrieves the instance variables from the stock
	    //
	    Text open = new Text("Open : " + currentStock.getOpen());
	    open.setFont(Font.font("Arial", FONTSIZE1 ));
	    display.add(open, 15, 10); 
	    
	    
	    Text high = new Text("High : " + currentStock.getHigh());
	    high.setFont(Font.font("Arial", FONTSIZE1));
	    display.add(high, 15, 15);
	    
	    Text low = new Text("Low : " + currentStock.getLow());
	    low.setFont(Font.font("Arial", FONTSIZE1));
	    display.add(low, 15, 20); 
	    
	    Text close = new Text("Close : " + currentStock.getClose());
	    close.setFont(Font.font("Arial", FONTSIZE1));
	    display.add(close, 20, 10); 
	    
	    Text volume = new Text("Volume : " + currentStock.getVolume());
	    volume.setFont(Font.font("Arial", FONTSIZE1));
	    display.add(volume, 20, 15); 
	    //
	    // *** Text ****
	    
	    
		return display;
	}*/
	
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StockApp Plus");
       /* 
        BorderPane root = new BorderPane();
        
        
        //*** The search bar at the top *** 
        //
        TextField searchField = new TextField("Symbol");
       
        Button searchButton = new Button("Search");		//Button calls the function to display the stock info
        
        searchButton.setOnAction(action -> {       					//This is a shortcut notation for setting events
        	root.setCenter(displayStockInfo(searchField.getText()));       	
        });
        
        HBox lookup = new HBox(searchField, searchButton);
        
        lookup.setAlignment(Pos.TOP_CENTER);
        
        root.setTop(lookup);
        //
        //*** Search bar end ***
        
       */
        
       // Scene scene = new Scene(root, 800, 500);
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
  //      primaryStage.setScene(scene);
 //       primaryStage.show();

    }

}