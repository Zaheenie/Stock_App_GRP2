package stocks;

public class stocks {
	
	public static void main(String[] args){
		
		NameSearchDownloader test = new NameSearchDownloader("MSN");
		String actual = test.getSymbol();
		System.out.println(actual);
		
	}

}