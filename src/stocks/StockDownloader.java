package stocks;
import org.json.JSONException;
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
			
			Calendar dailyCal = Calendar.getInstance();
			Calendar weeklyCal = Calendar.getInstance();
			Calendar monthlyCal = Calendar.getInstance();
			
			Date date = new Date();	
			
			dailyCal.setTime (date);
			weeklyCal.setTime (date);
			monthlyCal.setTime (date);			
			
			date = dailyCal.getTime(); 
			
			// Uncomment the line below in order use the previous day instead of the current day.
			// Program will not work past 12pm because the API doesn't have the new date yet.
			dailyCal.add(Calendar.DATE, -1); 
			
			int dayOfWeek = dailyCal.get(Calendar.DAY_OF_WEEK);
			
			if (dayOfWeek == 1) {
				dailyCal.add(Calendar.DATE, -2);
				weeklyCal.add(Calendar.DATE, -2);
				monthlyCal.add(Calendar.DATE, -2);
			}
			else if (dayOfWeek == 7) {
				dailyCal.add(Calendar.DATE, -1);
				weeklyCal.add(Calendar.DATE, -1);
				monthlyCal.add(Calendar.DATE, -1);
			}
			
			date = dailyCal.getTime(); 			
			Date dailyDate = date;
			Date weeklyDate = date;
			Date monthlyDate = date;
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			//Setting the date arrays		
			final int NUMBER_OF_DATES = 7;
			
			String [] dailyDates = new String [NUMBER_OF_DATES];
			String [] weeklyDates = new String [NUMBER_OF_DATES];
			String [] monthlyDates = new String [NUMBER_OF_DATES];
			

			int counter = 0;
			final int SIZE = 7;	
			String dailyString = "";
			String weeklyString = "";
			String monthlyString = "";
								
			while (counter < SIZE) {
				dailyCal.setTime(dailyDate); 
				weeklyCal.setTime(weeklyDate); 
				monthlyCal.setTime(monthlyDate); 
				
				dailyDate = dailyCal.getTime(); 
				weeklyDate = weeklyCal.getTime(); 
				monthlyDate = monthlyCal.getTime(); 
				
				dayOfWeek = dailyCal.get(Calendar.DAY_OF_WEEK);
				
				if(dayOfWeek == 1 || dayOfWeek == 7) {
					dailyCal.add(Calendar.DATE, -1);
					weeklyCal.add(Calendar.DATE, -1);
					monthlyCal.add(Calendar.DATE, -1);
					
					dailyDate = dailyCal.getTime(); 
					weeklyDate = weeklyCal.getTime(); 
					monthlyDate = monthlyCal.getTime(); 
					continue;					
				}
				dailyString = new SimpleDateFormat("yyyy-MM-dd").format(dailyDate);
				weeklyString = new SimpleDateFormat("yyyy-MM-dd").format(weeklyDate);	
				monthlyString = new SimpleDateFormat("yyyy-MM-dd").format(monthlyDate);		
				
				if((isAHoliday(dailyString)) || (isAHoliday(weeklyString)) || (isAHoliday(monthlyString))) {
					if ((isAHoliday(dailyString))) {
						dailyCal.add(Calendar.DATE, -1);
						dailyDate = dailyCal.getTime(); 
					}
					if ((isAHoliday(weeklyString))) {
						weeklyCal.add(Calendar.DATE, -1);
						weeklyDate = weeklyCal.getTime(); 
					}
					if ((isAHoliday(monthlyString))) {
						monthlyCal.add(Calendar.DATE, -1);	
						monthlyDate = monthlyCal.getTime(); 
					}
					continue;
				}
				dailyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(dailyDate);
				weeklyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(weeklyDate);	
				monthlyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(monthlyDate);	
				
				dailyCal.add(Calendar.DATE, -1);
				weeklyCal.add(Calendar.DATE, -7);			
				monthlyCal.add(Calendar.DATE, -28);		
				
				dailyDate = dailyCal.getTime(); 
				weeklyDate = weeklyCal.getTime(); 
				monthlyDate = monthlyCal.getTime(); 
				
				counter++;			
			}		
			String user_date = currentDate;
			
			// *** Dates End ***

//			Uncomment below to use a fixed date.				
//			final String user_date = "2019-03-25";
			//Here is Daily URL:
			URL dailyURL = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+tick+"&outputsize=full&apikey=I31NR8UIJ14RL4Y7");
			
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
			JSONObject weeklySeries = obj_JSONObject.getJSONObject("Time Series (Daily)");
			
			JSONObject obj_date = obj_timeSeries.getJSONObject(user_date);
			
			//Setting values for price history
					
			JSONObject [] dailyDateObjects = new JSONObject [NUMBER_OF_DATES];
			JSONObject [] weeklyDateObjects = new JSONObject [NUMBER_OF_DATES];
			JSONObject [] monthlyDateObjects = new JSONObject [NUMBER_OF_DATES];
			
			setDateObjects(dailyDateObjects, obj_timeSeries, dailyDates);
			setDateObjects(weeklyDateObjects, weeklySeries, weeklyDates);
			setDateObjects(monthlyDateObjects, obj_timeSeries, monthlyDates);
			
			setStockHistoryValues(dailyStockHistory, dailyDates, dailyDateObjects);
			setStockHistoryValues(weeklyStockHistory, weeklyDates, weeklyDateObjects);
			setStockHistoryValues(monthlyStockHistory, monthlyDates, monthlyDateObjects);

		
			//Price history stuff end					

			// *** Setting instance variables
			
			this.open = obj_date.getString("1. open");
			this.high = obj_date.getString("2. high");
			this.low = obj_date.getString("3. low");
			this.close = obj_date.getString("4. close");
			this.volume = obj_date.getString("5. volume");		
			
		} 
		catch (Exception e){
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
	public String getWeeklyHistoryValue(int columnNumber, int rowNumber) {return weeklyStockHistory[columnNumber][rowNumber];}
	public String getMonthlyHistoryValue(int columnNumber, int rowNumber) {return monthlyStockHistory[columnNumber][rowNumber];}

	
	public void setDateObjects(JSONObject [] dateObject, JSONObject dateString, String [] dateArray) throws JSONException {
		
		for(int i = 0; i < 7; i++) {
			dateObject[i] = dateString.getJSONObject(dateArray[i]);
		}
	}
	
	public void setStockHistoryValues(String [][] stockHistory, String [] stockDates, JSONObject [] dateObjects) throws JSONException {
		
		for (int i = 0; i < 7; i++) {
			stockHistory[0][i] = stockDates[i];
			stockHistory[1][i] = dateObjects[i].getString("1. open");
			stockHistory[2][i] = dateObjects[i].getString("2. high");
			stockHistory[3][i] = dateObjects[i].getString("3. low");
			stockHistory[4][i] = dateObjects[i].getString("4. close");
			stockHistory[5][i] = dateObjects[i].getString("5. volume");							
		}
		
	}
	
	boolean isAHoliday(String date) {
		String [] holidays = {
				"2019-04-19", "2019-02-18", "2018-11-21"
		};
		for (int i = 0; i < holidays.length - 1; i++) {
			if (date.equals(holidays[i])) {
				return true;
			}
		}
		return false;
		
	}
	// *** end of Getter methods

	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;

	private String [][] dailyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	private String [][] weeklyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	private String [][] monthlyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 	
	
}
