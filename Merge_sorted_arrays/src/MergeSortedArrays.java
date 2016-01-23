
public class MergeSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	int[] arr1 = {1,2,4,6,8};
		int[] arr1 = new int[10];
		arr1[0] = 1;
		arr1[1] = 2;
		arr1[2] = 4;
		arr1[3] = 6;
		arr1[4] = 8;
		int[] arr2 = {0,3,5,7,9};
		int res[] = Merge(arr1,arr2);
		for(int i=0;i<res.length;i++)
			System.out.println(res[i]);
	}
	
	public static int[] Merge(int[] arr1, int[] arr2)
	{
		int m = arr1.length;
		int n = arr2.length;
//		int i = m-1;
//		int j = n-1;
//		int k = m+n-1;
//		int[] res = new int[k];
		
//		while(k>=0)
//		{
//			if(j>0 || (i>=0 && arr1[i] > arr2[j])){
//				arr1[k--] = arr2[i--];
//			}
//			else
//			{
//				arr1[k--] = arr2[j--];
//			}
//		}
		int newarraySize = m + n - 1;
		m--;
		n--;
		while(m>=0 && n>=0)
		{
			if(arr1[m] > arr2[n])
			{
				arr1[newarraySize--] = arr1[m--];
			}
			else
			{
				arr1[newarraySize--] = arr2[n--];
			}
		}
		
		while(n>=0)
		{
			arr1[newarraySize--] = arr2[n--];
		}
		
		return arr1;
	}
}
