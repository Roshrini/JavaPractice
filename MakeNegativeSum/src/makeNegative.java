/*
 * Given an array of positive numbers. We can make any element of the array negative. 
 * We have to find the minimum positive sum after modifying numbers of array, 
 * by making some numbers negative.
 */


public class makeNegative {
	public static void main(String[] args) {
		int[] arr = {1,2,4,5,6,17,20};
	//	int[] arr = {1,2,4,5};
		System.out.println(minimumSum(arr));
	}
	public static int minimumSum(int[] array)
	{
	      int counter1, counter2, minimumSum;
	      int n = array.length;  //4
	      counter1 = array[n-1]; // 5
	      counter2 = array[n-2]; // 4
	      // It is assumed that the array is sorted.
	      int i = n-3; // 1
	      while(i>=0)
	      {
	          if(counter1 > counter2) // 5>4
	          { 
	              counter2 = counter2 + array[i]; // 6=4+2   
	              System.out.println("c2 "+counter2);
	          }
	          else // 5<6
	          {
	              counter1 = counter1 + array[i];
	              System.out.println("c1 "+counter1);
	          }
	          i--;
	      }
	      if(counter1 > counter2)
	      {
	          minimumSum = counter1 - counter2;
	      }
	      else
	          minimumSum = counter2 - counter1;
	      return minimumSum ;
	}
}