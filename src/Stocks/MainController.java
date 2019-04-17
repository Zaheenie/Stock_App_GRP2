package Stocks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class MainController {
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
		float openPrice1 = stock1.open1;
		float highPrice1 = stock1.high1;
		float lowPrice1 = stock1.low1;
		float closePrice1 = stock1.close1;
		float volume1 = stock1.volume1;
		
		// Get downloaded info for stock 2
		StockDownloader stock2 = new StockDownloader(stockInput2);
		float openPrice2 = stock2.open2;
		float highPrice2 = stock2.high2;
		float lowPrice2 = stock2.low2;
		float closePrice2 = stock2.close2;
		float volume2 = stock2.volume2;
		
		
		
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
