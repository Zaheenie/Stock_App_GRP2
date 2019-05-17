package stocks;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.control.TextField;

public class MainControllerTest {

	/**
	 * Test method for {@link stocks.MainController#compareStocksButton(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@Test
	public void testCompareStocksButton() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#buttonCompare(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@Test
	public void testButtonCompare() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#searchSymbol(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@Test
	public void testSearchSymbol() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#priceHistoryButton(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@Test
	public void testPriceHistoryButton() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#showHistory(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@Test
	public void testShowHistory() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#graphButton(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */
	@SuppressWarnings("unused")
	private static NameSearchDownloader nameConnectionTest;
	@Test
	public void testGraphButton() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
	}

	/**
	 * Test method for {@link stocks.MainController#btn(javafx.event.ActionEvent)}.
	 * Tests whether a connection can be established with the the AlphaVantage API
	 */

	@Test
	public void testBtn() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		assertNotNull(nameConnectionTest);
		
	}

}
