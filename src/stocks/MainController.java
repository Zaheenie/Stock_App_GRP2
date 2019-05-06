package stocks;

import java.text.DecimalFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class MainController {


	 @FXML
	 private TextField symbolField;
	 
	 @FXML
	 private TextField symbolField2;

	 @FXML
	 private Label stockOpen;

	 @FXML
	 private Label stockLow;

	 @FXML
	 private Label stockClose;

	 @FXML
	 private Text companyName;

	 @FXML
	 private Label stockHigh;

	 @FXML
	 private Label regionLabel;

	 @FXML
	 private Label currencyLabel;

	 @FXML
	 private Text currency;

	 @FXML
	 private Text region;

	 @FXML
	 private GridPane companyNameLabel;

	 @FXML
	 private Label stockVolume;
	 
	 @FXML
	 private TextField compareField1;
		
	 @FXML
	 private TextField compareField2;
	  
	 @FXML
	 private HBox row1;

	 @FXML
	 private HBox row2;
	 
	 @FXML
	 private HBox row3;
	
	 @FXML
	 private HBox row4;
	 
	 @FXML
	 private HBox row5;

	 @FXML
	 private HBox row6;

	 @FXML
	 private HBox row7;

	 @FXML
	 private HBox row8;
	 
	 @FXML
	 private HBox row9;

	 @FXML
	 private HBox row10;

	 @FXML
	 private HBox row11;

	 @FXML
	 private HBox row12;
	 
	 @FXML
	 private HBox row13;
	   
	 // *** End of private variables
	 
	 
	 @FXML
	  void compareStocksButton(ActionEvent event) {
			
			try {       	
				FXMLLoader loader = new FXMLLoader(getClass().getResource("compareStocksScreen.fxml"));
	        	Parent compareScreen = loader.load();
	            
	        	MainController controller = (MainController) loader.getController();
	        	        	
	        	Scene compareScene = new Scene(compareScreen);
	        	
	        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        	
	        	window.setScene(compareScene);
	        	window.show();
	      
	        }		
			catch (Exception e) {        	
	        	e.printStackTrace();
	        }             
	    }
	
	 /* Button not implemented yet
	 @FXML
	 void btn(ActionEvent event) {
			
		String stockInput1 = compareField1.getText();
		String stockInput2 = compareField2.getText();
			
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
    */
	//Calls the search Screen
	@FXML
	void searchSymbol(ActionEvent event) {
		try {
			
			NameSearchDownloader currentStockPlus = new NameSearchDownloader(symbolField.getText());
			StockDownloader currentStock = new StockDownloader(symbolField.getText());			        	
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("stockSearchScreen.fxml"));
        	Parent stockScreen = loader.load();          
        	MainController controller = (MainController) loader.getController();
        	
        	//Displays the Stock information
        	controller.companyName.setText(currentStockPlus.getName() + " (" + currentStockPlus.getSymbol() + ")");
        	
        	controller.region.setText(currentStockPlus.getRegion());
        	
        	controller.currency.setText(currentStockPlus.getCurrency());
        	
        	controller.stockOpen.setText(currentStock.getOpen());
        	
        	controller.stockHigh.setText(currentStock.getHigh());
        	
        	controller.stockLow.setText(currentStock.getLow());
        	
        	controller.stockClose.setText(currentStock.getClose());
        	
        	controller.stockVolume.setText(currentStock.getVolume());       	
        	
        	// Display end
        	
        	Scene stockScene = new Scene(stockScreen);
        	
        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
        	window.setScene(stockScene);
        	window.show();
        	
        }
        catch (Exception e) {
        	
        	e.printStackTrace();
        }       
	}
	
	//*** Testing calls price history
	@FXML
	void priceHistoryButton(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("priceHistoryScreen.fxml"));
	    	Parent histScreen = loader.load();
	    	
	    	MainController controller = (MainController) loader.getController();
	    	
	    	Scene histScene = new Scene(histScreen);
        	
        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
        	window.setScene(histScene);
        	window.show();
		}
		
		catch (Exception e) {
        	
        	e.printStackTrace();
        }       
	}
	
	@FXML
    void showHistory(ActionEvent event) {
		
		StockDownloader currentStock = new StockDownloader(symbolField2.getText());
		DecimalFormat fourDigitPrecision = new DecimalFormat("#0.0000");
		DecimalFormat volumePrecision = new DecimalFormat("#0");
		
		HBox [] oddRows = new HBox[] {
			row1, row3, row5, row7, row9, row11, row13
		}; 
		
		HBox [] evenRows = new HBox[] {
			row2, row4, row6, row8, row10, row12	
		};
				
		for (int i = 0; i < 7; i++) {
		
			oddRows[i].getChildren().clear();		
			Label [] rowLabels = new Label[6];
		
			for (int j = 0; j < 6; j++) {
				rowLabels[j] = new Label(currentStock.getDailyHistoryValue(j, i));
				rowLabels[j].setPrefSize(77.8, 32);
				rowLabels[j].setAlignment(Pos.CENTER);
				rowLabels[j].setFont(new Font("Arial", 12));				
				oddRows[i].getChildren().add(rowLabels[j]);
			}			
		}
		
		for (int i = 0; i < 6; i++) {
			
			evenRows[i].getChildren().clear();
			Label [] rowLabels = new Label[6];
		
			for (int j = 0; j < 6; j++) {
				
				boolean isPostive = false;				
				if (j == 0)
					rowLabels[j] = new Label("");
				else {				
					double value1 = Double.parseDouble(currentStock.getDailyHistoryValue(j, i));
					double value2 = Double.parseDouble(currentStock.getDailyHistoryValue(j, i + 1));
					double differenceValue = value1 - value2;
									
					isPostive =  (differenceValue > 0) ? true : false;					
					String difference;
					
					if (j != 5) {					
						difference = (isPostive) ?  "+" + fourDigitPrecision.format(value1 - value2) : 
													"" + fourDigitPrecision.format(value1 - value2);
					}
					else {					
						difference = (isPostive) ?  "+" + volumePrecision.format(value1 - value2) : 
													"" + volumePrecision.format(value1 - value2);
					}															
					rowLabels[j] = new Label(difference);
				}
				
				rowLabels[j].setPrefSize(77.8, 32);
				rowLabels[j].setAlignment(Pos.CENTER);
				rowLabels[j].setFont(new Font("Arial", 12));
				
				if (isPostive)
					rowLabels[j].setTextFill(Color.web("#00FF7F")); //green
				else 
					rowLabels[j].setTextFill(Color.web("#ff76a3")); //red
				
				evenRows[i].getChildren().add(rowLabels[j]);
			}
			
		}	
		
    }
	
	//Calls the graphScreen
	@FXML
    void graphButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("graphScreen.fxml"));
        	Parent graphScreen = loader.load();			
        	
        	Scene graphScene = new Scene(graphScreen);
        	
        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
        	window.setScene(graphScene);
        	window.show();
        	
		}
		
		catch (Exception e) {
        	
        	e.printStackTrace();
        }       
    }

	
	//new
	
	
	@FXML LineChart<String, Number> lineChart;
	@FXML Label lbl;
	
	@FXML
	private TextField userField1;
	
	@FXML
	private TextField userField2;
	
	@FXML
	ObservableList<String> data = FXCollections.observableArrayList("text1", "text2", "text3");
	ComboBox<String> stockInfo = new ComboBox<>(data);

	
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
		
		
		
		// Clear the chart legend
		lineChart.getData().clear();
		
		// Information for first compared stock
		XYChart.Series<String, Number> stock_1 = new XYChart.Series<String, Number>();
		stock_1.getData().add(new XYChart.Data<String, Number>("Open", openPrice1));
		stock_1.getData().add(new XYChart.Data<String, Number>("High", highPrice1));
		stock_1.getData().add(new XYChart.Data<String, Number>("Low", lowPrice1));
		stock_1.getData().add(new XYChart.Data<String, Number>("Close", closePrice1));
		//stock1.getData().add(new XYChart.Data<String, Number>("Friday", volume));

		stock_1.setName(stockInput1);
		
		// Information for second compared stock
		XYChart.Series<String, Number> stock_2 = new XYChart.Series<String, Number>();
		stock_2.getData().add(new XYChart.Data<String, Number>("Open", openPrice2));
		stock_2.getData().add(new XYChart.Data<String, Number>("High", highPrice2));
		stock_2.getData().add(new XYChart.Data<String, Number>("Low", lowPrice2));
		stock_2.getData().add(new XYChart.Data<String, Number>("Close", closePrice2));
		//stock2.getData().add(new XYChart.Data<String, Number>("Friday", 200));
		stock_2.setName(stockInput2);
		
		lineChart.getData().addAll(stock_1, stock_2);
		
		// Handler involved in checking for click events in graph
		for (final XYChart.Data<String, Number> data:stock_1.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				@Override
				public void handle (MouseEvent event) {
					lbl.setText(data.getXValue() + ": " + String.valueOf(data.getYValue()));
					Tooltip.install(data.getNode(), new Tooltip(data.getXValue() + ": " + String.valueOf(data.getYValue())));
				}
			});
		}
		
		for (final XYChart.Data<String, Number> data:stock_2.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				@Override
				public void handle (MouseEvent event) {
					lbl.setText(data.getXValue() + ": " + String.valueOf(data.getYValue()));
					Tooltip.install(data.getNode(), new Tooltip(data.getXValue() + ": " + String.valueOf(data.getYValue())));
				}
			});
		}
	}
}
