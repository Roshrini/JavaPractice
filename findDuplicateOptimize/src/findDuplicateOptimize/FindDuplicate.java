package findDuplicateOptimize;

public class FindDuplicate {

	public static void main(String[] args) {
		int[] input = {9,4,3,3,22,22,22,6,7,8,1,9};
		int [] output = sort(input);
		for(int i=0; i <output.length; i++)
		{
			System.out.print(output[i]+"  ");
		}
		System.out.println();
		int dupli = checkDuplicate(input);
		if(dupli == -99)
			System.out.println("No duplicate Found");
		else
			System.out.println("Duplicate Found "+checkDuplicate(input));
	}
	public static int[] sort(int[] input)
	{
		if(input.length == 0 || input==null)
			return input;
		return MergeSort(input, 0, input.length-1);
	}
	
	public static int[] MergeSort(int[] input, int start, int end)
	{
		if(start==end)
			return input;

		else if(end-start == 1){
			if(input[start] > input[end])
				swap(input, start, end);
		}
		else{
		int mid = ((int) Math.floor((start+end)/2));
		MergeSort(input, start, mid);
		MergeSort(input, mid+1, end);
		merge(input, start, end, mid);
		}

		return input;
	}

	static void merge(int[] input, int start, int end, int mid){
		int i = start;
		while(i <= mid){
				if(input[i] > input[mid+1]){
				swap(input, i, mid+1);
				for(int j=mid+1; j<end; j++){
					if(input[j] > input[j+1])
						swap(input, j, j+1);
				}
			}			
			i++;
		}		
	}
	
	public static void swap(int[] input, int i, int j)
	{
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static int checkDuplicate(int[] input)
	{
		for(int i=0;i<input.length-1;i++)
		{
			if(input[i]==input[i+1])
				return input[i];
		}
		return -99;
	}

}
