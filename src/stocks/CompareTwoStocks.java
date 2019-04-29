package stocks;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompareTwoStocks{
	

	@Override
    public void start(Stage primaryStage) {
              
      

    }
	
	@FXML
	private TextField userField1;
	
	@FXML
	private TextField userField2;
	
	@SuppressWarnings("unchecked")
	public void btn(ActionEvent event) {
		
		String stockInput1 = userField1.getText();
		String stockInput2 = userField2.getText();
		
		// Get downloaded info for stock 1
		
		StockDownloader stock1 = new StockDownloader(stockInput1);
		
		float openPrice1 = Float.parseFloat(stock1.getOpen());
		float highPrice1 = Float.parseFloat(stock1.getHigh());
		float lowPrice1 = Float.parseFloat(stock1.getLow());
		float closePrice1 = Float.parseFloat(stock1.getClose());
		float volume1 = Float.parseFloat(stock1.getVolume());
		
		// Get downloaded info for stock 2
		
		StockDownloader stock2 = new StockDownloader(stockInput2);
		
		float openPrice2 = Float.parseFloat(stock2.getOpen());
		float highPrice2 = Float.parseFloat(stock2.getHigh());
		float lowPrice2 = Float.parseFloat(stock2.getLow());
		float closePrice2 = Float.parseFloat(stock2.getClose());
		float volume2 = Float.parseFloat(stock2.getVolume());
	}
	
	//PSUEDOCODE
	//if openPrice1 > opnPrice2 
	//	make the number green and the other red
	//	repeat for the next 4 variables
		
}
