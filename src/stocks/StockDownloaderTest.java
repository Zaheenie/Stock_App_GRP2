package stocks;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class StockDownloaderTest {
	
	@SuppressWarnings("unused")
	private StockDownloader stockConnectTest;
	
	@Before
    public void setUp1() throws IOException, ParseException {
        stockConnectTest = new StockDownloader("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSN&outputsize=compact&apikey=I31NR8UIJ14RL4Y7");
    }

	@Before
	public void setUp() {		
		StockDownloader currentStock = new StockDownloader("ibm");		
	}
	
	@Test
	public void tickerMarkTest() {
	
	}
	
	
	
}
