import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

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
			e.printStackTrace();
   }
   assertEquals('P', fm.nodeMade['P'-'A'].ch);
   assertEquals(null, fm.nodeMade['M'-'A']);
   assertEquals('W', fm.nodeMade['P'-'A'].neighbors.get(0).ch);
   assertEquals(300, fm.costs['S'-'A']['T'-'A']);
   assertEquals(500, fm.costs['W'-'A']['Y'-'A']);
  }
  
  @Test
  public void EmptyInputFileTest() {
	  FlightMap fm = new FlightMap("empty_input.txt", "empty_out");
	  try {
		  fm.getFlightCosts();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  assertNotEquals(null, fm);
	  
	  //Test that output file has only header in it
	  Scanner scanner = null;
	  try {
		scanner = new Scanner(new File("empty_out"));
	  } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  //Count # of lines in output file
	  int count = 0;
	  while (scanner.hasNextLine()) {
	      count++;
	      scanner.nextLine();
	  }
	   assertEquals(1, count);
  }
  
  
  
  @Test
  public void DFSTest() {
	FlightMap fm = new FlightMap("input.txt", "out");
	  try {
		fm.getFlightCosts();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  //Test for Path from P to Z -- exists
	  Node f1 = fm.nodeMade['P'-'A'];
	  Node d1 = fm.nodeMade['Z'-'A'];
	  StringBuilder sb = new StringBuilder();
	  HashSet<Character> set = new HashSet<Character>();
	  assertNotEquals(0, fm.DFS(f1, d1, sb, fm.costs, set));
	  
	  //Test for Path from P to Q -- !exists
	  Node d2 = fm.nodeMade['Q'-'A'];
	  sb = new StringBuilder();
	  set.clear();
	  assertEquals(0, fm.DFS(f1, d2, sb, fm.costs, set));
	  
	  //Test for Path from S to R -- exists
	  Node f2 = fm.nodeMade['S'-'A'];
	  Node d3 = fm.nodeMade['R'-'A'];
	  sb = new StringBuilder();
	  set.clear();
	  assertNotEquals(0, fm.DFS(f2, d3, sb, fm.costs, set));
	  
	  //Test for Path from R to R -- exists
	  Node f3 = fm.nodeMade['R'-'A'];
	  sb = new StringBuilder();
	  set.clear();
	  assertNotEquals(0, fm.DFS(f3, d3, sb, fm.costs, set));
	  
	 //Test for Path from Z to P -- !exists
	  Node f4 = fm.nodeMade['Z'-'A'];
	  Node d4 = fm.nodeMade['P'-'A'];
	  sb = new StringBuilder();
	  set.clear();
	  assertEquals(0, fm.DFS(f4, d4, sb, fm.costs, set));	  
  }
  
  
  
}