
public class minDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	int[] input = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
		int[] input = {3, 5, 4, 2, 6, 3, 0, 0, 5, 4, 8, 3};
		int a=3;
		int b=6;
		int min = findMinDist(input,a,b);
		System.out.println(min);
	}
	
	public static int findMinDist(int[] input,int a, int b)
	{
		int prev =0;
		for(int i=0;i<input.length;i++)
		{
			if(input[i]==a || input[i]==b){
				prev = i;
				break;
		}
		}
		int min = Integer.MAX_VALUE;
		for(int i=prev+1;i<input.length;i++)
		{
			if (input[i] == a || input[i] == b)
			{
			if(input[i]==input[prev])
				prev = i;
			else if(input[i]==b && min > (i-prev)){
				if(min > (i-prev)){
					min = i-prev;
					prev = i;
				}
			}
			}	
		}
//		for (int i=prev; i < input.length; i++)
//		   {
//		 if (input[i] == a || input[i] == b)
//	      {
//	          // If the current element matches with any of the two then
//	          // check if current element and prev element are different
//	          // Also check if this value is smaller than minimm distance so far
//	          if ( input[prev] != input[i] && (i - prev) < min )
//	          {
//	             min = i - prev;
//	             prev = i;
//	          }
//	          else
//	             prev = i;
//	      }
//		   }
		return min;
	}
}
