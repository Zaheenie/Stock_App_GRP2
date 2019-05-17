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

// This class is a max of parsing and setting key values into data structures for the scene views to use

public class StockDownloader {
		
	public StockDownloader(String tick)
	{		
		try{
			final int NUMBER_OF_DATES = 7;
			final int SIZE = 7;	
			final int ONE_DAY = 1;
			final int TWO_DAYS = 2;
			final int ONE_WEEK = 7;
			final int ONE_MONTH = 28;
			final int SUNDAY = 1;
			final int SATURDAY = 7;
			
			//Calendar objects are created in order to seperately track dates for each sceneview category.
			Calendar dailyCal = Calendar.getInstance();
			Calendar weeklyCal = Calendar.getInstance();
			Calendar monthlyCal = Calendar.getInstance();
			
			//This line obtains the curreent date.
			Date date = new Date();	
			
			//Setting calendar objects to the current date.
			dailyCal.setTime (date);
			weeklyCal.setTime (date);
			monthlyCal.setTime (date);						
			date = dailyCal.getTime(); 
						
			//dailyCal.add(Calendar.DATE, -1);
			// *** Uncomment the line above in order use the previous day instead of the current day.
			// *** The API will not have the current day's values from 12:00 am - 5:00pm so it will crash 
			
			
			//The following segment is the start of obtaining valid dates from the JSON file.
			
			//This line obtains the current day in a numeric form. Sunday = 1, Monday = 2 ...etc
			int dayOfWeek = dailyCal.get(Calendar.DAY_OF_WEEK);
			
			
			//If the day is either Sunday or Saturday, push back the date to Friday, which is the last valid day for stocks
			if (dayOfWeek == SUNDAY) {
				dailyCal.add(Calendar.DATE, -TWO_DAYS);
				weeklyCal.add(Calendar.DATE, -TWO_DAYS);
				monthlyCal.add(Calendar.DATE, -TWO_DAYS);
			}
			else if (dayOfWeek == SATURDAY) {
				dailyCal.add(Calendar.DATE, -ONE_DAY);
				weeklyCal.add(Calendar.DATE, -ONE_DAY);
				monthlyCal.add(Calendar.DATE, -ONE_DAY);
			}
			
			//After making sure the current day isn't a Sunday or a Saturday, set these new dates as the starting points
			date = dailyCal.getTime(); 			
			Date dailyDate = date;
			Date weeklyDate = date;
			Date monthlyDate = date;
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			//These arrays are used to store the dates to be used for the price history functions
			String [] dailyDates = new String [NUMBER_OF_DATES];
			String [] weeklyDates = new String [NUMBER_OF_DATES];
			String [] monthlyDates = new String [NUMBER_OF_DATES];
			
			int counter = 0;
			
			//These string variables are used later in the code to check if the current date is a holiday
			String dailyString = "";
			String weeklyString = "";
			String monthlyString = "";
								
			while (counter < SIZE) {
				//This segment of code adjusts the calendar objects accroding o their increments.
				
				dailyCal.setTime(dailyDate); 
				weeklyCal.setTime(weeklyDate); 
				monthlyCal.setTime(monthlyDate); 
				
				dailyDate = dailyCal.getTime(); 
				weeklyDate = weeklyCal.getTime(); 
				monthlyDate = monthlyCal.getTime(); 
				
				dayOfWeek = dailyCal.get(Calendar.DAY_OF_WEEK);
				
				//This if block asks the calendar objects to ignore Sundays and Saturdays
				if(dayOfWeek == SUNDAY || dayOfWeek == SATURDAY) {
					dailyCal.add(Calendar.DATE, -ONE_DAY);
					weeklyCal.add(Calendar.DATE, -ONE_DAY);
					monthlyCal.add(Calendar.DATE, -ONE_DAY);
					
					dailyDate = dailyCal.getTime(); 
					weeklyDate = weeklyCal.getTime(); 
					monthlyDate = monthlyCal.getTime(); 
					continue;					
				}
				
				// *** This segment checks if the selected day is a holiday
				dailyString = new SimpleDateFormat("yyyy-MM-dd").format(dailyDate);
				weeklyString = new SimpleDateFormat("yyyy-MM-dd").format(weeklyDate);	
				monthlyString = new SimpleDateFormat("yyyy-MM-dd").format(monthlyDate);		
				
				//If the date is a holiday, it is ignored in favor of the previous day
				if((isAHoliday(dailyString)) || (isAHoliday(weeklyString)) || (isAHoliday(monthlyString))) {
					if ((isAHoliday(dailyString))) {
						dailyCal.add(Calendar.DATE, -ONE_DAY);
						dailyDate = dailyCal.getTime(); 
					}
					if ((isAHoliday(weeklyString))) {
						weeklyCal.add(Calendar.DATE, -ONE_DAY);
						weeklyDate = weeklyCal.getTime(); 
					}
					if ((isAHoliday(monthlyString))) {
						monthlyCal.add(Calendar.DATE, -ONE_DAY);	
						monthlyDate = monthlyCal.getTime(); 
					}
					continue;
				}				
				// *** Holiday check end
				
				
				//Valid dates are stored in yyyy-MM-dd format into the corresponding arrays
				dailyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(dailyDate);
				weeklyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(weeklyDate);	
				monthlyDates[counter] = new SimpleDateFormat("yyyy-MM-dd").format(monthlyDate);	
				
				dailyCal.add(Calendar.DATE, -ONE_DAY); //daily
				weeklyCal.add(Calendar.DATE, -ONE_WEEK); //weekly			
				monthlyCal.add(Calendar.DATE, -ONE_MONTH);	//monthly	
				
				dailyDate = dailyCal.getTime(); 
				weeklyDate = weeklyCal.getTime(); 
				monthlyDate = monthlyCal.getTime(); 
				
				counter++;			
			}		
			
			String userDate = currentDate;
			
			// *** Dates End ***
			
			// *** Connecting to the API ***
			
			//
			URL dailyURL = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+tick+"&outputsize=full&apikey=I31NR8UIJ14RL4Y7");
			
			//Connecting to the API
			URLConnection urlConn = dailyURL.openConnection();
			InputStreamReader instream = new InputStreamReader(urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(instream);
			StringBuffer response = new StringBuffer();
			
			//Reading the given JSON file into a String.
			String line;
			while((line = buff.readLine()) != null){
				response.append(line);				
			} buff.close();
			
			//Converting the string into a JSONobject
			JSONObject objJSONObject = new JSONObject(response.toString());		
			JSONObject objTimeSeries = objJSONObject.getJSONObject("Time Series (Daily)");
			JSONObject weeklySeries = objJSONObject.getJSONObject("Time Series (Daily)");		
			JSONObject objDate = objTimeSeries.getJSONObject(userDate);
			
			//Obtaining and Setting values for the scene views
					
			JSONObject [] dailyDateObjects = new JSONObject [NUMBER_OF_DATES];
			JSONObject [] weeklyDateObjects = new JSONObject [NUMBER_OF_DATES];
			JSONObject [] monthlyDateObjects = new JSONObject [NUMBER_OF_DATES];
			
			setDateObjects(dailyDateObjects, objTimeSeries, dailyDates);
			setDateObjects(weeklyDateObjects, weeklySeries, weeklyDates);
			setDateObjects(monthlyDateObjects, objTimeSeries, monthlyDates);
			
			setStockHistoryValues(dailyStockHistory, dailyDates, dailyDateObjects);
			setStockHistoryValues(weeklyStockHistory, weeklyDates, weeklyDateObjects);
			setStockHistoryValues(monthlyStockHistory, monthlyDates, monthlyDateObjects);

			this.open = objDate.getString("1. open");
			this.high = objDate.getString("2. high");
			this.low = objDate.getString("3. low");
			this.close = objDate.getString("4. close");
			this.volume = objDate.getString("5. volume");		
			
		} 
		catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	final int NUMBER_OF_COLUMNS = 6;
	final int NUMBER_OF_ROWS = 7;
	
	//Simple getter methods to return the chosen stock value
	public String getOpen() {return this.open;}
	public String getHigh() {return this.high;}
	public String getLow() {return this.low;}
	public String getClose() {return this.close;}
	public String getVolume() {return this.volume;}
	
	//Getter methods return a given stocks values
	public String getDailyHistoryValue(int columnNumber, int rowNumber) {return dailyStockHistory[columnNumber][rowNumber];}
	public String getWeeklyHistoryValue(int columnNumber, int rowNumber) {return weeklyStockHistory[columnNumber][rowNumber];}
	public String getMonthlyHistoryValue(int columnNumber, int rowNumber) {return monthlyStockHistory[columnNumber][rowNumber];}
	
	//Method takes in 
	public void setDateObjects(JSONObject [] dateObject, JSONObject dateString, String [] dateArray) throws JSONException {
		
		for(int i = 0; i < NUMBER_OF_ROWS; i++) {
			dateObject[i] = dateString.getJSONObject(dateArray[i]);
		}
	}
	
	//Setter function takes in dates and date obejcts and sets them into the given 2d array
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
	
	//Method takes a given date as an argument and returns a true if it's a holiday, false otherwise
	boolean isAHoliday(String date) {
		String [] holidays = {
				"2019-04-19", "2019-02-18", "2018-12-25", "2018-11-21"
		};
		for (int i = 0; i < holidays.length - 1; i++) {
			if (date.equals(holidays[i])) {
				return true;
			}
		}
		return false;		
	}

	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;

	private String [][] dailyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	private String [][] weeklyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 
	private String [][] monthlyStockHistory = new String[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS]; 	
	
}
