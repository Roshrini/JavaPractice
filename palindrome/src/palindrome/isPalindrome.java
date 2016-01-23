package palindrome;

public class isPalindrome {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 12345;
		int out = checkPalindrome(n);
		System.out.println(out);
	}
	
	public static int checkPalindrome(int n)
	{
		if(n < 9)
			return n;
		
		int rev = 0;
		while(n!=0)
		{
			rev = rev * 10 + n%10 ;
			n = n/10;
		}
		return rev;
	}
	
	
}
