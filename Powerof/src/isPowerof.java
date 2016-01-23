
public class isPowerof {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 100;
		System.out.println(Math.log10(n));
		//System.out.println(isPowerOfTwo(4));
		System.out.println(isPowerOfTen(n));
	}

	static boolean isPowerOfTwo(int n) {     
	    return ((n != 0) && (n & (n-1)) == 0);
	}
	static boolean isPowerOfTen(int n) {   
		if(StrictMath.log10(n)%1 == 0 && n>=1)
			return true;
		else
			return false;
	    
	}
}
