
public class quickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] input = {9, 2, 4, 7, 3, 7, 10};
		int l=0;
		int r=input.length-1;
		quicksort(input,l,r);
		for(int i=0; i<input.length;i++)
			System.out.println(input[i]+" ");
	}
	public static void quicksort(int[] input,int low,int high)
	{
		int n=input.length;
		int mid = low+(high-low)/2;
		int pivot = input[mid];
		if(input==null || input.length==0)
			return;
		if(low>=high)
			return;
		
		int i=low, j=high;
		while(i<=j)
		{
			while(input[i]<pivot)
				i++;
			while(input[j]>pivot)
				j--;
			if(i<=j)
			{
				int t = input[i];
				input[i] = input[j];
				input[j] = t;
				i++;
				j--;
			}
			
		}
		if(low<j)
			quicksort(input,low,j);
		if(high>i)
			quicksort(input,i,high);
		
	}

}
