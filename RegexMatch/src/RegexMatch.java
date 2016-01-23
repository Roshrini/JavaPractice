
public class RegexMatch {

	public static void main(String[] args) {
		String s = "aaa";
		String p = "*";
		System.out.println(isMatch(s,p));
	}
	
	public static boolean isMatch(String s, String p)
	{
		if(p.length()==0 && s.length()==0)
			return true;
		
		if(p.length() == 1 || (p.charAt(1) != '*'))
		{
			if(s.length() < 1 || p.charAt(0)!='.' && p.charAt(0)!=s.charAt(0))
				return false;
			return isMatch(s.substring(1),p.substring(1));
		}
		else
		{
			
			return false;
		}
	}
}
