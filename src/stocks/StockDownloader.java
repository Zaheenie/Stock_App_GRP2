package stocks;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StockDownloader {
		
	public StockDownloader(String tick)
	{
		
		try{
			final String user_date = "2019-03-25";
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
			JSONObject obj_date = obj_timeSeries.getJSONObject(user_date);
						
			
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
	
	// *** new Getter methods
	//
	public String getOpen() {return this.open;}
	public String getHigh() {return this.high;}
	public String getLow() {return this.low;}
	public String getClose() {return this.close;}
	public String getVolume() {return this.volume;}
	//
	// *** end of Getter methods
	
	
	// *** new instance variables
	//
	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;
	//
	// *** end of instance variables
	
}
