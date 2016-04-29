package mergeK_sorted;

//public class Solution {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
 
//  Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
 
	ListNode(int x) {
		val = x;
		next = null;
	}
}
 
public class Solution {
	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ArrayList<ListNode> n = new ArrayList<ListNode>();
		ArrayList<ArrayList<ListNode>> tot = new ArrayList<ArrayList<ListNode>>();
		tot.add(n);
	//	mergeKLists(tot);
	}
		
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists.size() == 0)
			return null;
 
		//PriorityQueue is a sorted queue
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>() {
					public int compare(ListNode a, ListNode b) {
						if (a.val > b.val)
							return 1;
						else if(a.val == b.val)
							return 0;
						else 
							return -1;
					}
				});
 
		//add first node of each list to the queue
		for (ListNode list : lists) {
			if (list != null)
				q.add(list);
		}
 
		ListNode head = new ListNode(0);
		ListNode p = head; // serve as a pointer/cursor
 
		while (q.size() > 0) {
			ListNode temp = q.poll();
			//poll() retrieves and removes the head of the queue - q. 
			p.next = temp;
 
			//keep adding next element of each list
			if (temp.next != null)
				q.add(temp.next);
 
			p = p.next;
		}
 
		return head.next;
	}
}