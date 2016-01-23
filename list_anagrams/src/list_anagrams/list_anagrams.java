package list_anagrams;
import java.util.*;

public class list_anagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ABC";
		String s1 = "EBAY";
		String s2 = "Yahoo";
		System.out.println("\nString " + s + ":\nPermutations: " + Permutation(s));
	//	System.out.println("\nString " + s1 + ":\nPermutations: " + Permutation(s1));
		//System.out.println("\nString " + s2 + ":\nPermutations: " + Permutation(s2));
	}

	public static Set<String> Permutation(String str)
	{
		Set<String> crunchifyResult = new HashSet<String>();
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			crunchifyResult.add("");
			return crunchifyResult;
		}
 
		char firstChar = str.charAt(0);
		String rem = str.substring(1);
		Set<String> words = Permutation(rem);
		for (String newString : words) {
			for (int i = 0; i <= newString.length(); i++) {
				crunchifyResult.add(newWords(newString, firstChar, i));
			}
		}
		return crunchifyResult;
	}
	public static String newWords(String str, char c, int j)
	{
		String first = str.substring(0, j);
		String last = str.substring(j);
		return first + c + last;
	}
}
