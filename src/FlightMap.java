import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * FlightMap.java
 * This program implements a search over a set of Nodes representing city
 * Its purpose is to put the flight paths from one city, and the cost and itinerary it takes to get to each destination.
 * @author Cerina da Graca
 * @since 2019-09-22
 */


public class FlightMap {
	/**
	 * FlightMap class
	 * This class implements a search over a set of Nodes representing city
	 * Its purpose is to put the flight paths from one city, and the cost and itinerary it takes to get to each destination.
	 * @author Cerina da Graca
	 * @since 2019-09-22
	 */
	
	
	int[][] costs = new int[26][26];	//adjacency matrix for the edges
	Node[] nodeMade = new Node[26];
	String inputFile;
	String outputFile;
	
	/**
	 * FlightMap constructor
	 * which will then return the cost of flights in the 
	 * file designated as output
	 * @param inputFile an input file
	 * @param outputFile an output file
	 */
	//constructor
	public FlightMap(String inputFile, String outputFile) {		
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	public void getFlightCosts() throws IOException {
		
		//read in a line from the file and make a node, insert cost into matrix
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(this.inputFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//first line-- remember the start node
		char head = '0';
		if(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			head = line.charAt(0);
		}
				
		while (scanner.hasNextLine()) {
		   String line = scanner.nextLine();
		   // process the line
		   String[] words = line.split(" ");  
		   char from = words[0].charAt(0);
		   char to = words[1].charAt(0);
		   int fare = Integer.valueOf(words[2]);
		   
		   //look for from in the array, if it is there, simply add new neighbor to its list. If not, make node, put in arr, add neighbor
		   if(nodeMade[from - 'A'] == null) {
			   //not there, make it
			   Node n = new Node(from);
			   nodeMade[from - 'A'] =  n;
//			   System.out.println("Created Node " + from);
		   }
		   
		   if(nodeMade[to - 'A'] == null) {
			   Node n = new Node(to);
			   nodeMade[to - 'A'] = n;
//			   System.out.println("Created Node " + to);
		   }
		   
		   //Add neighbor of the from node
		   nodeMade[from - 'A'].neighbors.add(nodeMade[to - 'A']);
		   
		   //update adjacency matrix
		   costs[from - 'A'][to - 'A'] = fare;     
		}
		
		//Write to file
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFile));
		
		//Print out header: Destination        Flight From P         Total Cost
		String heading = "Destination     Flight From " + head + "       " + "Total Cost";
		writer.append(heading);
		writer.append("\n");
		System.out.println("Destination     Flight From " + head + "    " + "Total Cost");
		
		//Loop over destination list
		for(Node node: nodeMade) {
			if(node != null) {
				if(node.ch != head) {
					StringBuilder sb = new StringBuilder();
					int returnCost = DFS(nodeMade[head-'A'], nodeMade[node.ch-'A'],sb, costs, new HashSet<Character>());
					if( returnCost != 0) {
						String str = node.ch + "               " + String.format("%-10s", sb.toString()) + "           " + returnCost + "\n";
						writer.append(str);
						System.out.print(node.ch + "               ");
						System.out.println(String.format("%-10s", sb.toString()) + "           " + returnCost);
					}
				}
			}
		}
		writer.close();
	}
	
	
	
	//Returns 0 if there is no path. Returns the cost of the flight from FromCity to DestCity if there is a path
	public int DFS(Node FromCity, Node DestCity, StringBuilder sb, int[][]costs, HashSet<Character> seen) {
		if(FromCity.ch == DestCity.ch) {
			sb.append(DestCity.ch);
			return -1;
		}

		//In a loop, return 0
		if(seen.contains(FromCity.ch)) {
			return 0;
		}
		
		//If I have no neighbors, I am a leaf that is not destination. Return false
		if(FromCity.neighbors == null || FromCity.neighbors.size() == 0) {
			return 0;
		}
			
		seen.add(FromCity.ch);
		
		sb.append(FromCity.ch + " ");

		for(Node neighbor : FromCity.neighbors) {
			StringBuilder newSB = new StringBuilder();
			int c = DFS(neighbor, DestCity, newSB, costs, seen);
			if(c == -1){  //base case hit, DestCity found
				sb.append(newSB);
				return costs[FromCity.ch-'A'][neighbor.ch-'A'];
			}
			else if(c > 0){
				sb.append(newSB);
				return c + costs[FromCity.ch-'A'][neighbor.ch-'A'];
			}
		}
		
		return 0;
	}
	
}


class Node {
	char ch;
	ArrayList<Node> neighbors;
	
	
	/**
	 * Node class
	 * which will then return the cost of flights in the 
	 * file designated as output
	 * @param ch somewords
	 */
	//Constructor
	Node(char ch){
		this.ch = ch;
		neighbors = new ArrayList<Node>();
	}
}
