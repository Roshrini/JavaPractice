
public class LIS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input={10, 9, 2, 5, 3, 7, 101, 18};
		int output = lis(input);
	//	for(int i=0; i<output.length;i++)
			System.out.println(output);
	}
	public static int lis(int[] input)
	{
		int[] dp= new int[input.length+1];
		for(int i=0;i<dp.length;i++)
			dp[i]=1;
		int maxlen=1;
		for(int i=1;i<=input.length;i++)
		{
			for(int j=1;j<i;j++)
			{
				if(input[i-1]>input[j-1])
				dp[i]=Math.max(dp[i], dp[j]+1);
			}
			maxlen=Math.max(maxlen, dp[i]);
		}
		
		return maxlen;
	}

}
