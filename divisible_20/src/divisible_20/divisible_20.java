package divisible_20;

public class divisible_20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int n=20;
long multiple = 1;

for ( long i = 2; i <= n; i++ ) {
    multiple =multiple* i / gcd(i, multiple);
}

System.out.println(multiple);
	}
	
	static long gcd(long a, long b) {
	    while ( 0 != b ) {
	        long temp = a;
	        a = b;
	        b = temp % b;
	    }
	  //  System.out.println(a);
	    return a;
	}

}
