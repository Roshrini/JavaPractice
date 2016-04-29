package findSubArray;

import java.util.ArrayList;

/*
 * Given an array of pairs of the form <a, b>. We have to find a sub-array such that 
 * the 1st element in the pairs are in increasing order and the sum of 2nd element of the pairs
 *  in the sub-array is maximum possible
 */
class IntPair {
	  // Ideally, name the class after whatever you're actually using 
	  // the int pairs *for.*
	  final int x;
	  final int y;
	  IntPair(int x, int y) {this.x=x;this.y=y;}
	  // depending on your use case, equals? hashCode?  More methods?
	}

public class findSubArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<IntPair> input = new ArrayList<IntPair>();
		input.add(new IntPair(3,4));
		input.add(new IntPair(4,4));
		input.add(new IntPair(6,4));
		input.add(new IntPair(8,4));
		input.add(new IntPair(3,4));
		input.add(new IntPair(1,4));
		input.add(new IntPair(2,4));
		input.add(new IntPair(7,4));
		
		ArrayList<IntPair> output = findSubArray(input);

	}
	
	public static ArrayList<IntPair> findSubArray(ArrayList<IntPair> input)
	{
		return input;	
	}

}
