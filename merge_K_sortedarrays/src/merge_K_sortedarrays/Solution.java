package merge_K_sortedarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

	public static List<Integer> mergeKSortedArray(List<List<Integer>> arrays) {
		//PriorityQueue is heap in Java 
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
	//	int total=0;
 
		//add arrays to heap
		for (int i = 0; i < arrays.size(); i++) {
			//System.out.print(arrays.get(i));
			queue.add(new ArrayContainer(arrays.get(i), 0));
	//		total = total + arrays.get(i).size();
		}
 
	//	int m=0;
	//	int result[] = new int[total];
		List<Integer> result  = new ArrayList<Integer>();
		
		//while heap is not empty
		while(!queue.isEmpty()){
			ArrayContainer ac = queue.poll();
		//	System.out.print(ac.arrays+" "+ac.index);
		//	result[m++]=ac.arr[ac.index];
		//	result.set(m++, ac.arrays.get(ac.index));
			result.add(ac.arrays.get(ac.index));
			
			if(ac.index < ac.arrays.size()-1){
				queue.add(new ArrayContainer(ac.arrays, ac.index+1));
			}
		}
 
		return result;
	}
 
	public static void main(String[] args) {
//		int[] arr1 = { 1, 3, 5, 7 };
//		int[] arr2 = { 2, 4, 6, 8 };
//		int[] arr3 = { 0, 9, 10, 11 };
		
		List<Integer> arr1 = new ArrayList<Integer>();
		List<Integer> arr2 = new ArrayList<Integer>();
		List<Integer> arr3 = new ArrayList<Integer>();
		
		List<List<Integer>> arrays = new ArrayList<List<Integer>>();
		
		arr1.add(1);
		arr1.add(3);
		arr1.add(5);
		arr1.add(7);
		
		arr2.add(2);
		arr2.add(4);
		arr2.add(6);
		arr2.add(8);
		
		arr3.add(0);
		arr3.add(9);
		arr3.add(10);
		arr3.add(11);
 
		arrays.add(arr1);
		arrays.add(arr2);
		arrays.add(arr3);
		
		List<Integer> result = mergeKSortedArray(arrays);
		//System.out.println(Arrays.toString(result));
		
		for(int i=0; i<result.size();i++)
			System.out.print(result.get(i)+" ");
	}

}
class ArrayContainer implements Comparable<ArrayContainer> {
	List<Integer> arrays;
	int index;
 
	public ArrayContainer(List<Integer> arrays, int index) {
		this.arrays = arrays;
		this.index = index;
	//	System.out.print(arrays+"  "+index+" ");
	}

	public int compareTo(ArrayContainer o) {
		System.out.println(this.arrays.get(this.index)+"  comp  "+o.arrays.get(o.index)+" ");
		return this.arrays.get(this.index) - o.arrays.get(o.index);
	}
}
