package stocks;

import static org.junit.Assert.*;
import org.junit.Test;

/*
 * In this test case we are using company ticker MSN
 */
public class NameSearchDownloaderTest {
	
	@SuppressWarnings("unused")
	private static NameSearchDownloader nameConnectionTest;
	
	NameSearchDownloader helper = new NameSearchDownloader("MSN");
	

	/**
	 * Test method for {@link stocks.NameSearchDownloader#NameSearchDownloader(java.lang.String)}.
	 * * Testing the URL connection to see that it works
	 */
	@Test
	public void testNameSearchDownloader() {
		nameConnectionTest = new NameSearchDownloader("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=MSN&apikey=I31NR8UIJ14RL4Y7");
		fail("No connection to URL");
	}

	/**
	 * Test method for {@link stocks.NameSearchDownloader#getSymbol()}.
	 */
	@Test
	public void testGetSymbol() {
		String expected = "MSN";
		assertEquals(expected,helper.getSymbol());
		fail("Symbol is not what was expected");
	}

	/**
	 * Test method for {@link stocks.NameSearchDownloader#getName()}.
	 */
	@Test
	public void testGetName() {
		String expected = "Emerson Radio Corp.";
		assertEquals(expected,helper.getName());
		fail("Company Name is not what was expected");
	}

	/**
	 * Test method for {@link stocks.NameSearchDownloader#getRegion()}.
	 */
	@Test
	public void testGetRegion() {
		String expected = "United States";
		assertEquals(expected,helper.getRegion());
		fail("Region is not what was expected");
	}

	/**
	 * Test method for {@link stocks.NameSearchDownloader#getCurrency()}.
	 */
	@Test
	public void testGetCurrency() {
		String expected = "USD";
		assertEquals(expected,helper.getCurrency());
		fail("Currency is not what was expected");
	}

}
