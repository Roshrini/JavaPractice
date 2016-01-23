
public class CountOccurances {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1, 2, 2, 3, 3, 3};
		int x = 3;
		System.out.println(countOcc(arr, x));
	}
	
	public static int countOcc(int[] arr, int x)
	{
		int f;
		int l;
		//int cnt = 0;
		int n= arr.length;
		f = first(arr,0,n,x);
		l = last(arr,f,n,x);
	//	System.out.println(f+"  "+l);
		if(f == -1 && l==-1)
			return 0;
		else if(f==l)
			return 1;
		else			
			return (l-f+1);
	}
	
	public static int first(int[] arr,int start,int end, int x)
	{
		if(end >= start)
		{
		//int mid = (start+end)/2;
		int mid = start+(end-start)/2;
		//if(( mid == 0 || x > arr[mid-1]) && arr[mid] == x )
		if(((mid == 0) && (arr[mid] == x)) ||
                ((arr[mid] == x) && (arr[mid - 1] < x)))
		{
			return mid;
		}
		else if(x>arr[mid])
			return first(arr,mid+1,end,x);
		else 
			return first(arr,start,mid-1,x);
		}
		return -1;
	}
	
	public static int last(int[] arr,int start,int end, int x)
	{
		if(end >= start)
		{
		//int mid = (start+end)/2;
		int mid = start+(end-start)/2;
		int n=arr.length;
		//if((mid == arr.length || x>arr[mid+1]) && arr[mid]==x)
		if (((mid == n) && (arr[mid] == x)) ||
                ((arr[mid] == x) && (arr[mid + 1] > x)))
		{
			return mid;
		}
		else if(x < arr[mid])
			return last(arr,start,mid-1,x);
		else
			return last(arr,mid+1,end,x);
		}
		return -1;
	}

}
