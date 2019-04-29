package stocks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class MainController {
	//new

	 @FXML
	 private TextField symbolField;

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

    
	//Calls the search Screen
	@FXML
	void searchSymbol(ActionEvent event) {
		try {
			
			NameSearchDownloader currentStockPlus = new NameSearchDownloader(symbolField.getText());
			StockDownloader currentStock = new StockDownloader(symbolField.getText());
			        	
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("stockSearch.fxml"));
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
	
	//Calls the graphScreen
	@FXML
    void graphButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("graphScreen.fxml"));
        	Parent graphScreen = loader.load();
        	MainController controller = (MainController) loader.getController();
        	
        	Scene graphScene = new Scene(graphScreen);
        	
        	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
        	window.setScene(graphScene);
        	window.show();
        	
		}
		
		catch (Exception e) {
        	
        	e.printStackTrace();
        }       
    }

	//calls CompareStocks***************************
	
	@FXML
    void compareStocksButton(ActionEvent event) {
		
		try {       	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("compareStock.fxml"));
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
	
	
	//new**********************************************
	
	
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
