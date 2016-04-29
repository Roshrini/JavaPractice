
public class practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {9, 2, 4, 7, 3, 7, 10};
		int len = input.length;
		int low=0;
		int high = len-1;
		quick(input,low,high);
		for(int i=0; i <len; i++)
			System.out.println(input[i]);
	}
	public static void quick(int[] input, int low, int high)
	{
		int mid = low+(high-low)/2;
		int pivot = input[mid];
		if(input.length==0 || input==null)
			return;
		if(low>=high)
			return;
		int i=low;
		int j=high;
		while(i<=j)
		{
		while(input[i]<pivot)
		{
			i++;
		}
		while(input[j]>pivot)
			j--;
		
		if(i<=j)
		{
			int temp = input[i];
			input[i] = input[j];
			input[j] = temp;
			i++;
			j--;
		}
		}
		if(low<j)
		{
			quick(input,low,j);
		}
		if(high>i)
		{
			quick(input,i,high);
		}
	}
}
