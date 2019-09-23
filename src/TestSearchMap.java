import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

public class TestSearchMap {
  
	@Test
	public void mainTest() throws IOException{
		SearchMap.main(new String[] {"input.txt", "out2"});
		System.out.println("Main passed.");
	}
	
	
	@Test
	public void classTest() {
		SearchMap s = new SearchMap();
		assertNotNull(s);
		SearchMap s2  = new SearchMap();
		assertNotEquals(s, s2);
		
	}
}