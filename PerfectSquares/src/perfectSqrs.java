
public class perfectSqrs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] mat = {{1,2,3,4},{1,2,3,4},{1,2,3,4}};
		System.out.println(mat.length);
		System.out.println(mat[0].length);
		System.out.println(numSquares(12));
	}

    public static int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
         
        int[] dp = new int[n + 1];
         
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
         
        return dp[n];
    }
}