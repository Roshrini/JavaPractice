package fibonacci;

public class fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 0 1 1 2 3 5 8 13
		int n=4;
		System.out.println(fib(n));

	}
	public static int fib(int n)
	{
		if(n==0)
			return 0;
		if(n==1 || n==2)
			return 1;
		int sum = fib(n-1)+fib(n-2);
		return sum;
	}

}
