package sort_array_bst;

public class sort_array_bst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {4, 2, 5, 1, 3};
		  int arr_size = arr.length;

		  int[] arr_new = printSorted(arr, 0, arr_size-1);
		  //for (int i=0; i<arr_new.length;i++)
		//  System.out.println("returned "+arr_new[i]);
	}
	public static int[] printSorted(int arr[], int start, int end)
	{     System.out.println("start "+start+"   "+end);
	  if(start > end)
	    return arr;
	  System.out.println("before entering "+start+"   "+end);
	  // print left subtree
	  printSorted(arr, start*2 + 1, end);
	  System.out.println("after first recursion " + start+"   "+end);
	  // print root
	  System.out.println(" "+ arr[start]);
	 
	  // print right subtree
	  printSorted(arr, start*2 + 2, end);  
	  System.out.println("after second recursion "+start+"  "+end);
	  return arr;
	}
	
	 
}
