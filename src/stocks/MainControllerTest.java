package stocks;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.control.TextField;

public class MainControllerTest {

	/**
	 * Test method for {@link stocks.MainController#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#start(javafx.stage.Stage)}.
	 */
	@Test
	public void testStartStage() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#compareStocksButton(javafx.event.ActionEvent)}.
	 */
	@Test
	public void testCompareStocksButton() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#buttonCompare(javafx.event.ActionEvent)}.
	 */
	@Test
	public void testButtonCompare() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#searchSymbol(javafx.event.ActionEvent)}.
	 */
	@Test
	public void testSearchSymbol() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#priceHistoryButton(javafx.event.ActionEvent)}.
	 */
	@Test
	public void testPriceHistoryButton() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#showHistory(javafx.event.ActionEvent)}.
	 */
	@Test
	public void testShowHistory() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stocks.MainController#graphButton(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@SuppressWarnings("unused")
	private static NameSearchDownloader nameConnectionTest;
	@Test
	public void testShowGraph() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#btn(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */

	@Test
	public void testGraphFunction() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
		
	}

}
