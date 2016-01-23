package wordLadder;
import java.util.*;
public class WordLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		String start = "hit";
		String end = "cog";
		System.out.println(ladderLength(start, end, dict));
	}
	public static int ladderLength(String start, String end, HashSet<String> dict)
	{
		int len = 0;
		for(int i=0; i <start.length(); i++)
		{
			char[] start_arr = start.toCharArray();

			for(char c='a'; c<='z'; c++)
			{
				if(c == start_arr[i])
					continue;
			
			start_arr[i] = c;
			String temp = new String ( start_arr ) ; if ( dict . contains (temp) ) {
			len ++;
			start = temp;
			if ( temp.equals( end ) ) {
			return len;
			}
			}
		}
		}
		return len;
	}
}
