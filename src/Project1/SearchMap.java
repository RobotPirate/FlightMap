package Project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SearchMap {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String inputFile = args[0];
//		String outputFile = args[1];
		
		int[][] cost = new int[26][26];	//adjacency matrix for the edges
				
		//read in a line from the file and make a node, insert cost into matrix
		File myFile = new File("input.txt");
        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//first line-- remember the start node
		char head;
		if(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			head = line.charAt(0);
		}
		
		Node[] nodeMade = new Node[26];
				
		while (scanner.hasNextLine()) {
		   String line = scanner.nextLine();
		   System.out.println(line);
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
			   System.out.println("Created Node " + from);
		   }
		   
		   if(nodeMade[to - 'A'] == null) {
			   Node n = new Node(to);
			   nodeMade[to - 'A'] = n;
			   System.out.println("Created Node " + to);

		   }
		   
		   //Add neighbor of the from node
		   nodeMade[from - 'A'].neighbors.add(nodeMade[to - 'A']);
		   
		   //update adjacency matrix
		   cost[from - 'A'][to - 'A'] = fare;
		}
		
		
	}

}
