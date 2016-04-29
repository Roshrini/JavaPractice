import java.util.*;

public class Solution {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = {
				{ 8, 2,  4}, 
				{ 0, 7,  1}, 
				{3, 7,  9}
				};
		
//		int[][] grid = {
//				{ 1, 2,  3}, 
//				{ 6, 5,  4}, 
//				{7, 8,  9}
//				};

		int length = 0;
		length = longestIncreasingPath(grid);
		System.out.println(length);

	}
	
    public static int longestIncreasingPath(int[][] mat) {
        int R = mat.length;
        if(R == 0) return 0;
        int C = mat[0].length;
        if(C == 0) return 0;
        int[][] ref = new int[R][C];

        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,1},{-1,-1},{1,-1}};
        int maxDepth = 1;

        for(int i = 0;i<R;i++){
            for(int j = 0;j<C;j++){
                // start bfs from unvisited node
                if(ref[i][j] == 0){
                    Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
                    queue.add(new ArrayList<Integer>(Arrays.asList(i,j)));
                    int depth = 1;
                    while(queue.size() > 0){
                        depth++;
                        int size = queue.size();
                        for(int k = 0;k<size;k++){
                            List<Integer> from = queue.poll();
                            int i1 = from.get(0);
                            int j1 = from.get(1);
                            for(int d=0;d<8;d++){
                                int i2 = i1 + directions[d][0];
                                if(i2 > -1 && i2 < R){
                                    int j2 = j1 + directions[d][1];
                                    if(j2 > -1 && j2 < C){
                                        if((mat[i2][j2] >= mat[i1][j1]) && depth > ref[i2][j2]){
                                        	System.out.println(mat[i1][j1]+"  "+mat[i2][j2]);
                                            ref[i2][j2] = depth;
                                            maxDepth = Math.max(maxDepth,depth);
                                            queue.add(new ArrayList<Integer>(Arrays.asList(i2,j2)));
                                        }
                                    }
                                }
                              //  break;
                            }
                         //   break;
                        }
                    }
                }
            }
        }
        return maxDepth;
    }
}