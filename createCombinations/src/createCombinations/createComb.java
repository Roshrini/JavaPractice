package createCombinations;

import java.util.HashSet;
import java.util.Set;

public class createComb {
	static HashSet<String> op = new HashSet<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Rosh";
		create(str);
		for(String o:op)
			System.out.println(o);
	}
	
	public static void create(String str)
	{
		permute("",str);
		
	}
	public static void permute(String perm, String word)
	{
		
		if(word==null || word.length()==0)
			op.add(perm+word);
		else
		{
			for(int i=0;i<word.length();i++)
			{
				permute(perm+word.charAt(i), word.substring(0,i)+word.substring(i+1, word.length()));
			}
		}
	}

}
