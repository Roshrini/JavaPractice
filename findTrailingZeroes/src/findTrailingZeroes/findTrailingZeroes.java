package findTrailingZeroes;

public class findTrailingZeroes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=30;
		System.out.println(findZeroes(n));
	}
	public static int findZeroes(int n)
	{
		int cnt=0;
		if(n==0)
			return 1;
		for(int i=5;i<=n;i=i*5)
		{
			cnt=cnt+n/i;
		}
		return cnt;
	}

}
