import java.util.Arrays;


public class ReverseWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseWords("the sky is blue"));
		System.out.println(reverseWordsInPlace("the sky is blue"));
		int arr[] = {-1,0,1,2,-1,-4};
		Arrays.sort(arr);
		for(int i=0;i<arr.length;i++)
			System.out.println(arr[i]);

	}
	public static String reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
 
		// split to words by space
		String[] arr = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; --i) {
			//System.out.println(i);
			if (!arr[i].equals("")) {
				sb.append(arr[i]).append(" ");
			  	}
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}
	
	public static String reverseWordsInPlace(String s)
	{
		String str = reverse(s,0,s.length()-1);
		System.out.println(str);
		int first = 0;
		int last = 0;
		int first1 = 0;
		s =str.trim();
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i)!=' ')
			{
				continue;
			}
		first1 = first;
		last = i;
		System.out.println(i);
		s = reverse(s,first1,last);
		first = i+1;
		
		}
		System.out.println(s);
		return s;
	}
	
	public static String reverse(String s, int start, int end)
	{
		StringBuilder sb = new StringBuilder(s.length());
	//	int start =0;
	//	 end = s.length()-1;
		for(int i=end; i>=start; i--)
		{
			sb.append(s.charAt(i));
		}
		return sb.substring(0, sb.length());
	}
}