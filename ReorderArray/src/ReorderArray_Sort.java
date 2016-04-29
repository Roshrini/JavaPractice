/* All elements less than A[i] should appear first, 
 * followed by elements equal to A[i] then, greater than A[i]
 * */

public class ReorderArray_Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {73,4,62,7,1,11,4,33,44,11,90};
		int n=5;
		int[] output = reorder_check(input,n);
		System.out.println("Output Array ");
		for(int i=0; i<output.length; i++)
		{
			System.out.print(" "+output[i]);
		}
	}
	
	 static int[] reorder(int[] input, int n)
	{
		 int first=0;
		 int middle=0;
		 int last = input.length-1;
		 int pivot = input[n];
		 
		 while(middle<=last)
		 {
			 if(input[middle]<pivot)
				 swap(input,first++,middle++);
			 else if(input[middle]==pivot)
				 ++middle;
			 else
				 swap(input,middle,last--);
		 }
		 return input;
	}

	public static void swap(int[] input, int i, int j) {
		// TODO Auto-generated method stub
		int t=input[i];
		input[i]=input[j];
		input[j]=t;
	}
	
	
	public static int[] reorder_check(int[] input, int n)
	{
		int start=0; int last=input.length-1;
		int mid=0;
		while(mid<=last)
		{
			if(input[start]>input[mid])
			{
				swap(input,start++,mid++);
			}
			else if(input[mid]==input[n])
			{
				mid++;
			}
			else
			{
				swap(input,mid,last--);
			}
		}
		
			return input;
	}
}
