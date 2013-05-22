package suffixTree;

import java.util.HashMap;

public class ByteArraySuffixTree {
	
	// Declare the first level HashMap
	private HashMap< Byte, Object > tree; 
	// Number of levels in the tree 
	private int treeLevels;
	
	
	// Constructor
	public ByteArraySuffixTree(){
		
		treeLevels = 0;
		tree = new HashMap< Byte, Object >();
		
	}
	
	// Put the byte array & value into the Tree
	public void put(byte[] bytes, Object val){
		
		// If this is the first object
		if ( this.treeLevels == 0){
			this.treeLevels = bytes.length;
		}
		
		// Inform if byte array of wrong size
		if (this.treeLevels != bytes.length){
			System.out.println( "Error, the byte array must be of size: " + this.treeLevels);
		}
		
		// Intermediate HashMap 'level' used to store our HashMap at each level
		HashMap< Byte, Object > level = tree; 		
		
		// Loop through the byte array & put into 
		for (int i = 0; i<treeLevels-1; i++){

			// Check if this level is 
			if ( level.containsKey(bytes[i]) ){

				// Get next Level, cast to HashMap
				level = (HashMap<Byte, Object>) level.get(bytes[i]);					
			}

			// If not, add to dict
			else{
				// Create next level
				level.put(bytes[i], new HashMap< Byte, Object >());					
				level = (HashMap<Byte, Object>) level.get(bytes[i]);
			}			
		}

		// Put value into the final level
		level.put(bytes[treeLevels-1], val);
	}
	
	
	// Get value out of the Tree
	public Object get(byte[] bytes){
		
		// Inform if byte array of wrong size
		if (this.treeLevels != bytes.length){
			System.out.println( "Error, the byte array must be of size: " + this.treeLevels);
		}
		
		HashMap< Byte, Object > level = tree; 	
		
		// Loop through level dicts and get the value for the byte array
		for (int i = 0; i<treeLevels-1; i++){
			level = (HashMap<Byte, Object>) level.get(bytes[i]);	
		}
		
		return level.get( bytes[treeLevels-1] );
	}
	
	
	// Remove an entry from the Tree
	public void remove(byte[] bytes){
		
		// Inform if byte array of wrong size
		if (this.treeLevels != bytes.length){
			System.out.println( "Error, the byte array must be of size: " + this.treeLevels);
		}

		// Call the recursive function
		removeRecur(tree, bytes, 0);	
	}
	
	// Recursive method
	public void removeRecur(HashMap<Byte, Object> levelTree, byte[] bytes, int level){
				
		// If the Final Level
		if ( level+1 == treeLevels){
			// Remove value entry from the dict
			levelTree.remove( bytes[level] );
			
		}
		else{
			
			HashMap<Byte, Object> nextDict = (HashMap<Byte, Object>) levelTree.get(bytes[level]);
			
			// Call the next level with this dict
			removeRecur( nextDict, bytes, level+1);
				
			// Check size of the dict-- if empty, delete entry
			if ( nextDict.size() == 0){
					
				//System.out.println("Remove tree at level: " + level);
				levelTree.remove( bytes[level] );
			}
				
		}
	}

	// Main harness
	public static void main(String args[]){
		
		int levels = 8;
		
		byte[] byteA = new byte[levels];
		byte[] byteB = new byte[levels];
		byte[] byteC = new byte[levels];

		String val = "Put Value Here";
		String valB = "Second Value";
		String valC = "Third Value";

		String str = "ABCDEFGH";
		String strB = "ABCDEFGI";
		String strC = "POKDLGXD";
		
		
		for (int i = 0;  i< levels; i++){
			byteA[i] = (byte) str.charAt(i);
			byteB[i] = (byte) strB.charAt(i);
			byteC[i] = (byte) strC.charAt(i);
		}
		
		ByteArraySuffixTree sTree = new ByteArraySuffixTree();
		
		// Put into the tree
		sTree.put(byteA, val);
		sTree.put(byteB, valB);
		sTree.put(byteC, valC);
		
		// Get the value using the ByteArray
		System.out.println(sTree.get(byteA));
		System.out.println(sTree.get(byteB));
		System.out.println(sTree.get(byteC));
		
		// Remove the Entry from the tree
		sTree.remove(byteA);
		sTree.remove(byteB);
		sTree.remove(byteC);
		
	}
}
