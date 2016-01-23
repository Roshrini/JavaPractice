package word_break;

import java.util.HashSet;

public class wordBreak {
	public static HashSet<String> dict = new HashSet<String>();
	public static void main(String[] args) {
		
		dict.add("Leet");
		dict.add("Code");

		String input = "LeetCode";
		System.out.println(wordBreaker(input));

	}
	public static boolean wordBreaker(String input)
	{
		//boolean flag = false;
		return checkIfExists(input, dict, 0);
	//	return flag;
	}
	
	public static boolean checkIfExists(String input, HashSet<String> dict, int start)
	{
		if(start == input.length())
			return true;
		
		
		for(String str : dict)
		{
			int len = str.length();
			int end = len + start;
			if(end>input.length())
				continue;
			if(input.substring(start,start+len).equals(str))
			{
				if(checkIfExists(input,dict,start+len))
				return true;
			}
		}
		return false;
	}

}
