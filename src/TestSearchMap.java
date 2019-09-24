import static org.junit.Assert.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class TestSearchMap {
  
	@Test
	public void mainTest() throws IOException{
		SearchMap.main(new String[] {"input.txt", "out2"});
		Scanner scanner = null;
		  try {
			scanner = new Scanner(new File("out2"));
		  } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }

		  //Check output
		  if(scanner.hasNextLine()) {
		      scanner.nextLine();
		  }
		  String line2 = scanner.nextLine();
		  String[] words = line2.split(" ");
		  assertEquals("R", words[0]);	
	}
	
	
	@Test
	public void classTest() {
		SearchMap s = new SearchMap();
		assertNotNull(s);
		SearchMap s2  = new SearchMap();
		assertNotEquals(s, s2);
		
	}
}