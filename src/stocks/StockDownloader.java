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
			final String user_date = "1998-01-02";
			//Here is Daily URL:
			URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+tick+"&outputsize=full&apikey=I31NR8UIJ14RL4Y7");
			
			URLConnection urlConn = url.openConnection();
			InputStreamReader instream = new InputStreamReader(urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(instream);
			StringBuffer response = new StringBuffer();
			
			String line;
			while((line = buff.readLine()) != null){
				response.append(line);
				
			} buff.close();
			
			//Prints Everything in JSON File
			//System.out.println(response.toString());
			JSONObject obj_JSONObject = new JSONObject(response.toString());
			
			JSONObject obj_timeSeries = obj_JSONObject.getJSONObject("Time Series (Daily)");
			JSONObject obj_date = obj_timeSeries.getJSONObject(user_date);
			
			//System.out.println(obj_timeSeries);
			System.out.println("Amount of Entries: "+obj_timeSeries.length());
			
			System.out.println(user_date);
			
			System.out.println("open: "+obj_date.getString("1. open"));
			System.out.println("high: "+obj_date.getString("2. high"));
			System.out.println("low: "+obj_date.getString("3. low"));
			System.out.println("close: "+obj_date.getString("4. close"));
			System.out.println("volume: "+obj_date.getString("5. volume"));
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
