package kthLargest;
import java.util.PriorityQueue;

public class kthLargest {

	public static void main(String[] args) {
		int[] arr =
			  { 3, 46, 2, 56, 3, 38, 93, 45, 6, 787, 34, 76, 
			    44, 6, 7, 86, 8, 44, 56 };
		int k=3;
		System.out.println(FindkthLargest(arr,k));

	}
	public static int FindkthLargest(int[] arr, int k)
	{
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for(int i=0;i<arr.length; i++)
		{
			heap.add(arr[i]);
		}
		
		int n=0;
		int len = heap.size();
		for(int i=arr.length; i>=k; i--)
		{
			n = heap.poll();
		}
		return n;
	}
}
