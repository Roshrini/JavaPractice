
public class Even_fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int sum = 0 ;
		    int x1 = 1;
		    int x2 = 2;
		    while ( x1 < 4000000 ) {
		        if ( (x1 & 1) == 0 ){    // x % 2 == 0
		            sum += x1;
		        }
		        x2=x1+x2;                // x2 = sum
		        x1=x2-x1;                // x1 = the old value of x2
		    }
		    System.out.println(sum);

	}

}
