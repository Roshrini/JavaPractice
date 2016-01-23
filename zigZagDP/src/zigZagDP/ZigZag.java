package zigZagDP;

public class ZigZag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
				600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
				67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
				477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
				249, 22, 176, 279, 23, 22, 617, 462, 459, 244};
		System.out.println(longestZigZag(input));

	}
	
	public static int longestZigZag(int[] A){
		  int n = A.length;
		  int[][] Z = new int[n][2];
		  for(int i = 0; i < Z.length; i++){
		    Z[i] = new int[2];
		  }
		  Z[0][0] = 1;
		  Z[0][1] = 1;
		  
		  int best = 1;
		  
		  for(int i = 1; i < n; i++){
		    for(int j = i-1; j>= 0; j--){
		      if(A[j] < A[i]) 
		    	  Z[i][0] = Math.max(Z[j][1]+1,Z[i][0]);
		      if(A[j] > A[i]) 
		    	  Z[i][1] = Math.max(Z[j][0]+1, Z[i][1]);
		    }
		    best = Math.max(best, Math.max(Z[i][0],Z[i][1]));
		  }
		  return best;
		}

}
