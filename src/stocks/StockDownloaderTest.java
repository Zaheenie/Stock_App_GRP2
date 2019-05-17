package stocks;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class StockDownloaderTest {
	
	@Test //Tests if the date is a holiday or not
	public void holidayTest() {
		StockDownloader currentStock = new StockDownloader("ibm");		
		Calendar dailyCal = Calendar.getInstance();
		Date date = new Date();	
		
		dailyCal.setTime (date);
		date = dailyCal.getTime(); 
		
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);		
		String error = "Date is a holiday";
		
		assertFalse(error, currentStock.isAHoliday(currentDate));
	}
	
	@Test
	public void dataMemberRetrievalTest() {
		StockDownloader msftStock = new StockDownloader("msft");
		
		String failedOpenMessage = "Failed to obtain open value";
		assertFalse(failedOpenMessage, msftStock.getOpen().equals("")); //Testing if open was set 
		
		String failedHighMessage = "Failed to obtain high value";
		assertFalse(failedHighMessage, msftStock.getHigh().equals("")); //Testing if high was set 
		
		String failedLowMessage = "Failed to obtain low value";
		assertFalse(failedLowMessage, msftStock.getLow().equals("")); //Testing if low was set 
		
		String failedCloseMessage = "Failed to obtain close value";
		assertFalse(failedCloseMessage, msftStock.getClose().equals("")); //Testing if close was set 
		
		String failedVolumeMessage = "Failed to obtain volume value";
		assertFalse(failedVolumeMessage, msftStock.getVolume().equals("")); //Testing if volume was set 
	}
	
	@Test
	public void dailyHistoryArrayTest () {
		StockDownloader googStock = new StockDownloader("goog");
		
		boolean isDailyHistoryFull = true;
		String failMessage ="At least one element failed to be set into the array";
		
		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < 7; j++) {
				if (googStock.getDailyHistoryValue(i, j).equals("")) {	
					//If any of the elements in the array is the null string, test fails
					isDailyHistoryFull = false;
					break;
				}
			}
				
		}
		
		assertTrue(failMessage, isDailyHistoryFull);
	}
	
	@Test
	public void weeklyHistoryArrayTest () {
		StockDownloader ibmStock = new StockDownloader("ibm");
		
		boolean isWeeklyHistoryFull = true;
		String failMessage ="At least one element failed to be set into the array";
		
		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < 7; j++) {
				
				if (ibmStock.getWeeklyHistoryValue(i, j).equals("")) {	
					//If any of the elements in the array is the null string, test fails
					isWeeklyHistoryFull = false;
					break;
				}
			}
				
		}
		
		assertTrue(failMessage, isWeeklyHistoryFull);
	}
	
	@Test
	public void MonthlyHistoryArrayTest () {
		StockDownloader fbStock = new StockDownloader("fb");
		
		boolean isMonthlyHistoryFull = true;
		String failMessage ="At least one element failed to be set into the array";
		
		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < 7; j++) {
				
				if (fbStock.getMonthlyHistoryValue(i, j).equals("")) {	
					//If any of the elements in the array is the null string, test fails
					isMonthlyHistoryFull = false;
					break;
				}
			}
				
		}
		
		assertTrue(failMessage, isMonthlyHistoryFull);
	}
	
}