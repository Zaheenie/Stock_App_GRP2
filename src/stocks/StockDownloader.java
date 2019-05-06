package stocks;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class StockDownloader {
		
	public StockDownloader(String tick)
	{		
		try{
			// *** Code to obtain valid dates ***
			
			Calendar cal = Calendar.getInstance();
			Date date = new Date();	
			
			cal.setTime (date);
			
			date = cal.getTime(); 
			
			// Uncomment the line below in order use the previous day instead of the current day.
			// Program will not work past 12 because the API doesn't have the new date yet.
			//cal.add(Calendar.DATE, -1);
			
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			
			if (dayOfWeek == 1) 
				cal.add(Calendar.DATE, -2);
			else if (dayOfWeek == 7)
				cal.add(Calendar.DATE, -1);
			
			date = cal.getTime(); 			
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			//Setting the date arrays
			
			String [] dailyDates = new String [7];

			int counter = 0;
			final int SIZE = 7;	
			
			while (counter < SIZE) {
				cal.setTime(date); 
				dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				
				if(dayOfWeek == 1 || dayOfWeek == 7) {
					cal.add(Calendar.DATE, -1);
					date = cal.getTime(); 
					continue;					
				}				
				dailyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(date);				
				cal.add(Calendar.DATE, -1);				
				date = cal.getTime(); 
				counter++;			
			}		
			String user_date = currentDate;
			
			// *** Dates End ***

//			Uncomment below to use a fixed date.				
//			final String user_date = "2019-03-25";
			//Here is Daily URL:
			URL dailyURL = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+tick+"&outputsize=compact&apikey=I31NR8UIJ14RL4Y7");
			
			URLConnection urlConn = dailyURL.openConnection();
			InputStreamReader instream = new InputStreamReader(urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(instream);
			StringBuffer response = new StringBuffer();
						
			String line;
			while((line = buff.readLine()) != null){
				response.append(line);
				
			} buff.close();
			
			//Prints Everything in JSON File
			JSONObject obj_JSONObject = new JSONObject(response.toString());
			
			JSONObject obj_timeSeries = obj_JSONObject.getJSONObject("Time Series (Daily)");
			JSONObject obj_date = obj_timeSeries.getJSONObject(user_date);
			
			//
			
			//Setting values for price history
			
			
			JSONObject [] dateObjects = new JSONObject [7];
			
			for(int i = 0; i < 7; i++) {
				dateObjects[i] = obj_timeSeries.getJSONObject(dailyDates[i]);
			}
			
			for (int i = 0; i < 7; i++) {
				dailyStockHistory[0][i] = dailyDates[i];
				dailyStockHistory[1][i] = dateObjects[i].getString("1. open");
				dailyStockHistory[2][i] = dateObjects[i].getString("2. high");
				dailyStockHistory[3][i] = dateObjects[i].getString("3. low");
				dailyStockHistory[4][i] = dateObjects[i].getString("4. close");
				dailyStockHistory[5][i] = dateObjects[i].getString("5. volume");							
			}

			
			//Price history stuff end			
			
			// *** new stuff, storing the values
			//
			this.open = obj_date.getString("1. open");
			this.high = obj_date.getString("2. high");
			this.low = obj_date.getString("3. low");
			this.close = obj_date.getString("4. close");
			this.volume = obj_date.getString("5. volume");
			//		
			// *** end of value storing
			
			
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	final int NUMBER_OF_COLUMNS = 6;
	final int NUMBER_OF_ROWS = 7;
	
	// *** Getter methods
	//
	public String getOpen() {return this.open;}
	public String getHigh() {return this.high;}
	public String getLow() {return this.low;}
	public String getClose() {return this.close;}
	public String getVolume() {return this.volume;}
	
	public String getDailyHistoryValue(int columnNumber, int rowNumber) {return dailyStockHistory[columnNumber][rowNumber];}
	//
	// *** end of Getter methods
	
	
	// ***  instance variables
	//
	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;
	//
	// *** end of instance variables
	
	private String [][] dailyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	private String [][] weeklyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	private String [][] monthlyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	
	
}
