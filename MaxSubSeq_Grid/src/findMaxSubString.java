//
//public class findMaxSubString {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

import java.util.ArrayList;

/*
find longest increasing sub sequence in 2d array. 
(bit more expl..) 
ex: finding length of the snake in snake game 
--------- 
the sequence must not be diagonally. 
but it can be any like top-bootm,bottom-left-top ........ 
increasing means one step 
ex: 10,11,12,13 (correct) 
12,14,15,20(wrong) 
Ex: input: consider 4x4 grid 
2 3 4 5 
4 5 10 11 
20 6 9 12 
6 7 8 40 

output : 4 5 6 7 8 9 10 11 12
*/

public class findMaxSubString{

public static void main(String[] args){
  findMaxSubString(grid);

}

private static void findMaxSubString(int[][] grid){

	for (int i=0; i<grid.length; i++)
		for (int j=0; j<grid[0].length; j++){
			resetState();
			subsequence.add(grid[i][j]);
			state[i][j] = false;
			findSubSequence(grid, 1, i, j);
	}
	System.out.println("Max sub string is:" + maxsequence.size());
	for (Object item:maxsequence)
		System.out.print(item + " ");

}
// 1,0, 0,1, -1,0, 0,-1, 1,1, 1,-1, -1,1 -1,-1
private static void findSubSequence(int[][] grid, int d, int i, int j){
	boolean flag = true;
	for(int ind=0; ind<knn.length; ind+=2){
		
		int ii = i + knn[ind];
		int jj = j + knn[ind+1];
		if (check(grid, ii, jj, i, j) && state[ii][jj]){
			flag = false;
			state[ii][jj] = false;
			subsequence.add(grid[ii][jj]);
			findSubSequence(grid, d+1, ii, jj);
			state[ii][jj] = true;
			subsequence.remove(subsequence.size()-1);		
		}
	}

	if (d>max_length && flag){
		max_length = d;
		maxsequence = (ArrayList<Object>) subsequence.clone();
		
	}
	return;

}

private static void resetState(){
	for (int i=0; i<grid.length; i++)
		for (int j=0; j<grid[0].length; j++)
			state[i][j] = true;

	subsequence.clear();

}

private static boolean check(int[][] grid, int ii, int jj, int i, int j){
	if (ii<0 || ii>=grid.length || jj<0 || jj>=grid[0].length) return false;
	return(grid[ii][jj]>=grid[i][j]);
}

private static ArrayList<Integer> subsequence = new ArrayList<Integer>();
private static ArrayList<Object> maxsequence = new ArrayList<Object>();
private static int[] knn = {1,0, 0,1, -1,0, 0,-1, 1,1, 1,-1, -1,1, -1,-1};
private static int max_length = 0;
//private static int[][] grid = {
//								{ 2, 3,  4, 5}, 
//								{ 4, 5,  7, 6}, 
//								{20, 6,  8, 9}, 
//								{ 6, 7,  8, 10}
//								};

//private static int[][] grid = {
//{ 8, 2,  4}, 
//{ 0, 7,  1}, 
//{3, 7,  9}
//};

private static int[][] grid = {
		{1, 1, 1}, 
		{1, 1, 1}, 
		{1, 1, 1},
		{0, 0, 0}
		};
private static boolean[][] state = new boolean[100][100];

}