
import java.io.IOException;

public class SearchMap {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String inputFile = args[0];
//		String outputFile = args[1];
		
		FlightMap fm = new FlightMap("input.txt", "output.txt");
//		System.out.println("In SearchMap");
		try{
			fm.getFlightCosts();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
		}
		
		//returns the outputFile string
		
				
		
//		File myFile = new File("input.txt");
//        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());
		
		
		
		
		
	}

}
