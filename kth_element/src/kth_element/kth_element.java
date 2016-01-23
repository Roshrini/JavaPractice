
package kth_element;

import java.util.Stack;

public class kth_element {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 4, 3, 7, 1, 2, 5 };
		int k = 5;
		int n = findKthLargest(nums, k);
		System.out.println(n);
		
	//	Stack<TreeNode> stack = new Stack<TreeNode>();
	}

	public static int findKthLargest(int[] nums, int k) {
		if (k < 1 || nums == null) {
			return 0;
		}

		return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
	}

	public static int getKth(int k, int[] nums, int start, int end) {

		int pivot = nums[end];

		int left = start;
		int right = end;

		while (true) {

			while (nums[left] < pivot && left < right) {
				left++;
			}
	//		System.out.println("left value "+left);
			while (nums[right] >= pivot && right > left) {
				right--;
			}
	//		System.out.println("right value "+right);
			if (left == right) {
				break;
			}

			swap(nums, left, right);
		}

		swap(nums, left, end);
		
		System.out.println("rosh"+k+"  "+left);
		for(int i=0;i<nums.length;i++)
		System.out.print(nums[i]+" , ");
		
		System.out.println();
		
		if (k == left + 1) {
			return pivot;
		} else if (k < left + 1) {
			System.out.println("left part ");
			return getKth(k, nums, start, left - 1);
		} else {
			System.out.println("right part ");
			return getKth(k, nums, left + 1, end);
		}
	}

	public static void swap(int[] nums, int n1, int n2) {
		int tmp = nums[n1];
		nums[n1] = nums[n2];
		nums[n2] = tmp;
	}
}
