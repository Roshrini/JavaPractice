package intStr;

public class inttostr {
	public static void main(String[] args)
	{
		int n = 123;
		System.out.println(change2(n));
	}
	
	public static StringBuilder change(int n)
	{
		String str = "";
		StringBuilder newstr= new StringBuilder();
		if(n<0)
			return newstr;
		int cnt=0;
		while(n>0)
		{
			int digit=n%10;  
			n=n/10; 
			str=str+digit*(int)Math.pow(10, cnt)+"+";
			cnt++;
		}
		String tid=str.substring(0, str.length()-1);
		String[] arr =tid.split("\\+");
		
		for(int i=arr.length-1;i>=0;i--)
		{
			newstr=newstr.append(arr[i]);
			if(i>0)
			newstr=newstr.append("+");
		}
		return newstr;
	}
	
	public static StringBuilder change2(int n)
	{
		String input = String.valueOf(n);
		StringBuilder sb = new StringBuilder();
		int cnt=input.length()-1;
		for(int i=0; i<input.length();i++)
		{	
			sb.append(Character.getNumericValue(input.charAt(i))*(int)Math.pow(10, cnt));
			if(cnt>0)
			sb.append("+");
			cnt--;
		}
		return sb;
	}
}
