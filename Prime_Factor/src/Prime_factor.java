
public class Prime_factor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long n=100L;
/*		for(int i=2;i<n/2;i++)
		{
			if(n%i==0 && isPrime(i))
			{
				System.out.println(" "+i);
			}
		}*/
		System.out.println(largestPrimeFactor(n));

	}
	public static boolean isPrime(int n)
	{
		for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	
	}
	
	public static int largestPrimeFactor(long number)
	{ 
		int i; 
		long copyOfInput = number; 
		for (i = 2; i <= copyOfInput; i++) 
		{ 
			if (copyOfInput % i == 0) 
			{ copyOfInput /= i; 
			i--;
			System.out.println(copyOfInput);
			}
			}
		return i; 
		}

}
