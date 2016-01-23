
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] input = {10,22,9,33,21,50,41,60,80};
		int n = input.length;
		System.out.println("output "+helper(input,n));
	}
	public static int helper(int[] input, int n)
	{
		int max = 1;
		max = LIS(input, n, max);
		return max;
	}
	
	public static int LIS(int[] input, int n, int max)
	{
		if(n == 1)
			return 1;
		
		int res, max_end = 1;
		
		for(int i=1; i<n; i++)
		{
			res = LIS(input,i, max);
			if(input[i-1] < input[n-1] && res+1 > max_end)
			{
				max_end = res+1;
			}
		}
		return  max_end;
	}

}
