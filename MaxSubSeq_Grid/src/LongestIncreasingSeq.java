
public class LongestIncreasingSeq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] grid = {
//				{ 8, 2,  4}, 
//				{ 0, 7,  1}, 
//				{3, 7,  9}
//				};
//		
//		int[][] grid = {
//				{5, 6, 8, 9, 0}, 
//				{5, 7, 8, 4, 9}, 
//				{3, 2, 1, 0, 6}
//				};
		
		int[][] grid = {
				{1, 1, 1}, 
				{1, 1, 1}, 
				{1, 1, 1},
				{0, 0, 0}
				};
		
		
//		int[][] grid = {
//				{ 1, 2,  3}, 
//				{ 6, 5,  4}, 
//				{7, 8,  9}
//				};

		int length = 0;
		length = longestSeq(grid);
		System.out.println(length);

	}
	public static int longestSeq(int[][] grid)
	{
		if(grid==null)
			return 0;
		int[][] dp = new int [grid.length][grid[0].length];
		int[][] visited = new int[grid.length][grid[0].length];
		int len=0;
		for(int i=0; i <grid.length; i++)
		{
			for(int j=0; j<grid[0].length; j++)
			{
				if(dp[i][j]==0)
				{
					len = Math.max(len, dfs(grid,dp,visited,i,j));
				}
			}
		}
		return len;
	}
	public static int dfs(int[][] grid, int[][] dp, int[][] visited, int i, int j)
	{
		int b=0,f=0,u=0,d=0;
		int bu=0,fu=0,bd=0,fd=0;
		int row=grid.length;
		int col=grid[0].length;
		//if(visited[i][j]==1)
			//return visited[i][j];
		
		if(j+1<col && (grid[i][j+1]>=grid[i][j] ))
		{
			f=dfs(grid,dp,visited,i,j+1);
			//System.out.println(grid[i][j+1]+"  "+grid[i][j]);
		}
		else if(j>0 && (grid[i][j-1]>=grid[i][j]))
		{
			b=dfs(grid,dp,visited,i,j-1);
			//System.out.println(grid[i][j-1]+"  "+grid[i][j]);
		}
		else if(i+1<row && (grid[i+1][j]>=grid[i][j]))
		{
			d=dfs(grid,dp,visited,i+1,j);
		//	System.out.println(grid[i+1][j]+"  "+grid[i][j]);
		}
		else if(i>0 && (grid[i-1][j]>=grid[i][j]))
		{
			u=dfs(grid,dp,visited,i-1,j);
		//	System.out.println(grid[i-1][j]+"  "+grid[i][j]);
		}
		
		else if(i>0 && j>0 && grid[i-1][j-1]>=grid[i][j])
			bu=dfs(grid,dp,visited,i-1,j-1);
		else if(i>0 && j+1<col && grid[i-1][j+1]>=grid[i][j])
			fu=dfs(grid,dp,visited,i-1,j+1);
		else if(i+1<row && j>0 && grid[i+1][j-1]>=grid[i][j])
			bd=dfs(grid,dp,visited,i+1,j-1);
		else if(i+1<row && j+1<col && grid[i+1][j+1]>=grid[i][j])
			fd=dfs(grid,dp,visited,i+1,j+1);
		//visited[i][j]=1;
		dp[i][j] = Math.max(Math.max(Math.max(u,d), Math.max(b,f)),  Math.max(Math.max(bu, bd), Math.max(fu, fd)))+1;
		//System.out.println(dp[i][j]);
		return dp[i][j];
		
	}
}
