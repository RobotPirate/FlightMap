import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestFlightMap {
  @Test
  public void NodeConstructorTest() {
   Node node = new Node('c');
   assertEquals('c', node.ch);
  }
  
  @Test
  public void FlightMapConstructorTest() {
   FlightMap fm = new FlightMap("in", "out");
   assertEquals("in", fm.inputFile);
   assertEquals("out", fm.outputFile);
   assertEquals(0, fm.costs[25][25]);
   fm.costs[3][5] = 16;
   assertEquals(16, fm.costs[3][5]);
   assertEquals(null, fm.nodeMade[25]);
  }
  
  @Test
  public void getFlightCostsTest() {
   FlightMap fm = new FlightMap("input.txt", "out");
   assertEquals("input.txt", fm.inputFile);
   try {
	fm.getFlightCosts();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   assertEquals('P', fm.nodeMade['P'-'A'].ch);
   assertEquals(null, fm.nodeMade['M'-'A']);
   assertEquals('W', fm.nodeMade['P'-'A'].neighbors.get(0).ch);
   assertEquals(300, fm.costs['S'-'A']['T'-'A']);
   assertEquals(500, fm.costs['W'-'A']['Y'-'A']);
  }
  
  
  
}