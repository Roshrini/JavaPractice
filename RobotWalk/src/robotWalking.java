import java.util.HashMap;


//# ----------
//# User Instructions:
//# 
//# Create a function compute_value which returns
//# a grid of values. The value of a cell is the minimum
//# number of moves required to get from the cell to the goal. 
//#
//# If a cell is a wall or it is impossible to reach the goal from a cell,
//# assign that cell a value of 99.
//# ----------

public class robotWalking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] grid = {{0, 1, 0, 0, 0, 0},
		        {0, 1, 0, 0, 0, 0},
		        {0, 1, 0, 0, 0, 0},
		        {0, 1, 0, 0, 0, 0},
		        {0, 0, 0, 0, 1, 0}};
		int[] goal = {(grid.length)-1, (grid[0].length)-1};
		int cost = 1; //# the cost associated with moving from a cell to an adjacent one
//String a = "abc";
        HashMap<Character, String> check= new HashMap<Character, String>();

		int[][] delta = {{-1, 0 },// # go up
		{ 0, -1}, //# go left
		{ 1, 0 }, //# go down
		{ 0, 1 }} ;//# go right

		int[] delta_name = {'^', '<', 'v', '>'};
		int[][] value=new int[grid.length][grid[0].length];
		value=compute_value(grid,goal,value,delta,cost);
		
		System.out.println();
		
		for(int i=0; i<value.length;i++)
		{
			for(int j=0; j<value[0].length; j++)
			{
				System.out.print("  "+value[i][j]);
			}
			System.out.println();
		}
	}


public static int[][] compute_value(int[][] grid, int[] goal, int[][] value, int[][] delta, int cost){
 //   # ----------------------------------------
//    # insert code below
//    # ----------------------------------------
//    
//    # make sure your function returns a grid of values as 
//    # demonstrated in the previous video.
	
	for(int i=grid.length-1; i>=0; i--)
	{
		for(int j=grid[0].length-1;j>=0; j--)
		{
			if(i==goal[0] && j==goal[1])
			{
				value[i][j]=0;
			}
			else if(grid[i][j]==1)
			{
				value[i][j]=999;
			}
			else
			{
				value[i][j]=99;
			}
		}
	}
	
	
	int min=0;

	for(int n=0; n<grid.length*grid[0].length;n++)
	{
	for(int i=0; i<grid.length; i++)
	{
		for(int j=0; j<grid[0].length; j++)
		{
			for(int k=0; k<delta.length; k++)
			{
				System.out.println("i  "+i+"j  "+j+"k  "+k);
			if(i+delta[k][0]>=0 && j+delta[k][1]>=0 && (value[i][j]!=999) && i+delta[k][0]<grid.length && j+delta[k][1]<grid[0].length && grid[i+delta[k][0]][j+delta[k][1]]==0 )
			{
				min = value[i+delta[k][0]][j+delta[k][1]] + 1;
                if (min < value[i][j])
                {
                    value[i][j] = min;
                }
			}
			else if(value[i][j]==999)
			{
				break;
			}
			}
				
		}
	}
}
	
	return value;
}
}