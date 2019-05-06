package stocks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class NameSearchDownloader {
	
	public NameSearchDownloader(String tick)
	{		
		try{
			final String num = "0:";
			
			///Here is Search Endpoint URL
			URL searchURL = new URL("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords="+tick+"&apikey=I31NR8UIJ14RL4Y7");
			
			URLConnection urlConn = searchURL.openConnection();
			InputStreamReader instream = new InputStreamReader(urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(instream);
			
			StringBuffer response = new StringBuffer();			
			String line;
			
			while((line = buff.readLine()) != null){
				response.append(line);
				
			} buff.close();
			
			//making an JSON object for search endpoint to parse through the data
			JSONObject searchOBJ = new JSONObject(response.toString());
			JSONArray searchEndpoint = searchOBJ.getJSONArray("bestMatches");
			JSONObject searchIndex = searchEndpoint.getJSONObject(0);
			
			// *** new stuff, storing the values
			//
			this.tickerSymbol = searchIndex.getString("1. symbol");
			this.name = searchIndex.getString("2. name");
			this.region = searchIndex.getString("4. region");
			this.currency = searchIndex.getString("8. currency");
			//		
			// *** end of value storing
			
		} 
		catch (Exception e){
			e.printStackTrace();
		}		
	}
		// *** Getter methods
		public String getSymbol() {return this.tickerSymbol;}
		public String getName() {return this.name;}
		public String getRegion() {return this.region;}
		public String getCurrency() {return this.currency;}
		
		// *** Instance variables
		private String tickerSymbol;
		private String name;
		private String region;
		private String currency;

}
