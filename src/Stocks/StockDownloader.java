package Stocks;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class StockDownloader {
		
	public float open1;
	public float high1;
	public float low1;
	public float close1;
	public float volume1;
	
	public float open2;
	public float high2;
	public float low2;
	public float close2;
	public float volume2;
	public StockDownloader(String tick)
	{
		
		try{
			final String user_date = "2017-10-04";
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
			
			//convert strings to floats
			open1 = Float.parseFloat(obj_date.getString("1. open"));
			high1 = Float.parseFloat(obj_date.getString("2. high"));
			low1 = Float.parseFloat(obj_date.getString("3. low"));
			close1 = Float.parseFloat(obj_date.getString("4. close"));
			volume1 = Float.parseFloat(obj_date.getString("5. volume"));
			
			open2 = Float.parseFloat(obj_date.getString("1. open"));
			high2 = Float.parseFloat(obj_date.getString("2. high"));
			low2 = Float.parseFloat(obj_date.getString("3. low"));
			close2 = Float.parseFloat(obj_date.getString("4. close"));
			volume2 = Float.parseFloat(obj_date.getString("5. volume"));
			
			System.out.println("open: "+open1);
			System.out.println("high: "+high1);
			System.out.println("low: "+low1);
			System.out.println("close: "+close1);
			System.out.println("volume: "+volume1);
			
			System.out.println("open: "+open2);
			System.out.println("high: "+high2);
			System.out.println("low: "+low2);
			System.out.println("close: "+close2);
			System.out.println("volume: "+volume2);
			
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
