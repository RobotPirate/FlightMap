
import java.io.IOException;

public class SearchMap {

	public static void main(String[] args) throws IOException {

		String inputFile = args[0];
		String outputFile = args[1];
		
		FlightMap fm = new FlightMap(inputFile, outputFile);

		try{
			fm.getFlightCosts();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
		}
	}

}
