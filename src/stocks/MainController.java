package stocks;

import java.text.DecimalFormat;
import javafx.application.Application;
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


public class MainController extends Application{
	
	public static void main(String [] args) {		
		launch(args);				
	}

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StockApp Plus");
     
        try {       	
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("stockSearchScreen.fxml"));
        	
        	Parent content = loader.load();
        	Scene scene = new Scene(content);
        	
        	primaryStage.setScene(scene);
            primaryStage.show();        	
        }
        catch (Exception e) {    	
        	e.printStackTrace();
        }            
    }


	 @FXML
	 private TextField symbolField, symbolField2;

	 @FXML
	 private Label stockOpen, stockLow, stockClose, stockHigh, stockVolume;

	 @FXML
	 private Text companyName, currency, region;

	 @FXML
	 private Label regionLabel, currencyLabel;

	 @FXML
	 private GridPane companyNameLabel;

	 @FXML
	 private TextField compareField1, compareField2;
  
	 @FXML
	 private HBox row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13;
	 
	 @FXML
	 private TextField comp1, comp2;

	 @FXML
	 private Text region1, currency1, open1, high1, low1, close1, volume1, compName1;
	 
	 @FXML
	 private Text region2, currency2, open2, high2, low2, close2, volume2, compName2;
	 
	   
	 // *** End of private variables
	 
	 @FXML
	  void compareStocksButton(ActionEvent event) {
			
			try {       	
				FXMLLoader loader = new FXMLLoader(getClass().getResource("compareStocksScreen.fxml"));
	        	Parent compareScreen = loader.load();
	                    	        	
	        	Scene compareScene = new Scene(compareScreen);	        	
	        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        	
	        	window.setScene(compareScene);
	        	window.show();	      
	        }		
			catch (Exception e) {        	
	        	e.printStackTrace();
	        }             
	    }
	
	 @FXML
		public void buttonCompare(ActionEvent actionEvent) {
			
			NameSearchDownloader currentStockPlus = new NameSearchDownloader(comp1.getText());
			StockDownloader currentStock = new StockDownloader(comp1.getText());
						
			//Prints everything for company 1
			compName1.setText(currentStockPlus.getName() + " (" + currentStockPlus.getSymbol() + ")");
	    	region1.setText(currentStockPlus.getRegion());        	
	    	currency1.setText(currentStockPlus.getCurrency());        	
	    	open1.setText(currentStock.getOpen());        	
	    	high1.setText(currentStock.getHigh());        	
	    	low1.setText(currentStock.getLow());        	
	    	close1.setText(currentStock.getClose());        	
	    	volume1.setText(currentStock.getVolume());
	    	
	    	NameSearchDownloader currentStockPlus2 = new NameSearchDownloader(comp2.getText());
			StockDownloader currentStock2 = new StockDownloader(comp2.getText());
			
	    	//Prints everything for company 2
	    	compName2.setText(currentStockPlus2.getName() + " (" + currentStockPlus2.getSymbol() + ")");
	    	region2.setText(currentStockPlus2.getRegion());        	
	    	currency2.setText(currentStockPlus2.getCurrency());        	
	    	open2.setText(currentStock2.getOpen());        	
	    	high2.setText(currentStock2.getHigh());        	
	    	low2.setText(currentStock2.getLow());        	
	    	close2.setText(currentStock2.getClose());        	
	    	volume2.setText(currentStock2.getVolume());
	    	
		}
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
	
	 @FXML
	 private HBox combo;
	 
	
	 @FXML
	 private ComboBox<String> historySelectionBox = new ComboBox<>();
	 
	//*** Testing calls price history
	 @FXML
	 public void initialize() {
		 historySelectionBox.getItems().removeAll(historySelectionBox.getItems());
		 historySelectionBox.getItems().addAll("Daily", "Weekly", "Monthly");
		 historySelectionBox.getSelectionModel().select("Daily");
	 }
	 
	@FXML
	void priceHistoryButton(ActionEvent event) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("priceHistoryScreen.fxml"));
	    	Parent histScreen = loader.load();    	
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
		
		//Used to obtain the stock ticker 
		StockDownloader currentStock = new StockDownloader(symbolField2.getText());
		
		//The decimalformat objects are used to format the values which can have a large number of decimals
		DecimalFormat fourDigitPrecision = new DecimalFormat("#0.0000");
		DecimalFormat volumePrecision = new DecimalFormat("#0");
		
		//The odd rows display the stock values. Putting them into an array makes it easier to manipulate
		HBox [] oddRows = new HBox[] {
			row1, row3, row5, row7, row9, row11, row13
		}; 
		
		//The even rows display the changes. Putting them into an array makes it easier to manipulate
		HBox [] evenRows = new HBox[] {
			row2, row4, row6, row8, row10, row12	
		};
		
		//This obtains the choice from the drop down menu. The choices being daily, weekly, monthly
		String currentSelection = historySelectionBox.getValue();
		
		//This for loop obtains the values from the data members of the current stock and displays them onto the oddrows
		for (int i = 0; i < 7; i++) {
		
			oddRows[i].getChildren().clear();		
			Label [] rowLabels = new Label[6];
		
			for (int j = 0; j < 6; j++) {
				rowLabels[j] = (currentSelection.equals("Daily")) ? new Label(currentStock.getDailyHistoryValue(j, i)) : 
							   (currentSelection.equals("Weekly")) ? new Label(currentStock.getWeeklyHistoryValue(j, i)) : 
								new Label(currentStock.getMonthlyHistoryValue(j, i));
				rowLabels[j].setPrefSize(77.8, 32);
				rowLabels[j].setAlignment(Pos.CENTER);
				rowLabels[j].setFont(new Font("Arial", 12));				
				oddRows[i].getChildren().add(rowLabels[j]);
			}			
		}	
		
		//This for loop obtains the differences from the values in their corresponding values in the odd rows and displays
		//them in the even rows
		for (int i = 0; i < 6; i++) {
			
			evenRows[i].getChildren().clear();
			Label [] rowLabels = new Label[6];
		
			for (int j = 0; j < 6; j++) {
				
				boolean isPostive = false;	
				//The line below is used to ignore the first label of the even row, which is just an empty label used to line up the rest of the labels
				if (j == 0)
					rowLabels[j] = new Label("");
				else {				
					//Logic to obtain the changes between the dates
					
					double value1 = (currentSelection.equals("Daily")) ? Double.parseDouble(currentStock.getDailyHistoryValue(j, i)) : 
									(currentSelection.equals("Weekly")) ? Double.parseDouble(currentStock.getWeeklyHistoryValue(j, i)) : 
									Double.parseDouble(currentStock.getMonthlyHistoryValue(j, i));	
					
					double value2 = (currentSelection.equals("Daily")) ? Double.parseDouble(currentStock.getDailyHistoryValue(j, i + 1)) : 
									(currentSelection.equals("Weekly")) ? Double.parseDouble(currentStock.getWeeklyHistoryValue(j, i + 1)) : 
									Double.parseDouble(currentStock.getMonthlyHistoryValue(j, i + 1));					
					
					double differenceValue = value1 - value2;
									
					isPostive =  (differenceValue > 0) ? true : false;					
					String difference;
					
					//Special logic used to format the volume value which is much longer than the other values
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
				
				//Setting the obtained values into the even rows
				rowLabels[j].setPrefSize(77.8, 32);
				rowLabels[j].setAlignment(Pos.CENTER);
				rowLabels[j].setFont(new Font("Arial", 12));
				
				//Logic to displayed the changes in different colors depending on if it's positive or not
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
	
	
	@FXML 
	LineChart<String, Number> lineChart;
	
	@FXML 
	Label lbl;
	
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
