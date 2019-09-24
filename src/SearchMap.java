
import java.io.IOException;




/**
 * SearchMap.java
 * This program is meant to be run by client to 
 * create a schedule of possible flights from 
 * a given source city
 * @author Cerina da Graca
 * @since 2019-09-22
 */
public class SearchMap {
	
	/**
	 * Main
	 * Precondition: input file and output file passed in
	 * Postcondition: output file will contain flight paths if possible
	 * @param args -- inputfile and outputfile
	 * @throws IOException if files are not provided or otherwise not proper
	 */

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
