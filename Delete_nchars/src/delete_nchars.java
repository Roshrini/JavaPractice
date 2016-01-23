
public class delete_nchars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="762388";
		int n=2;
		String new_str;
		new_str=delete(str,n);

	}
	public static String delete(String str, int n)
	{
		String new_str="";
		int[] digits=new int[str.length()];
		int add;
		add = str.length()-n;
		char[] lowest = new char[add];
		if(n==0)
			return str;
		
		int len=str.length();
		if(len<n)
			return str;
		else if (len > n)
		{
			for(int i=0;i<str.length();i++)
			{
				digits[i]=(int)str.charAt(i);
			}
		}
			
		int close[] = new int[str.length()];
		
		for(int i=str.length()-1 ; i>0 ; i--)
		{
			
		}
		
		
		return new_str;
	}

}
