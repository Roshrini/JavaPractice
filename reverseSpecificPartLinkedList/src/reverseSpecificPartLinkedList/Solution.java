package reverseSpecificPartLinkedList;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		// Creating a linked list
		Node head = new Node(1);
		list.addToTheLast(head);
		list.addToTheLast(new Node(2));
		list.addToTheLast(new Node(3));
		list.addToTheLast(new Node(4));
		list.addToTheLast(new Node(5));
		list.addToTheLast(new Node(5));
		list.addToTheLast(new Node(7));
		int m = 2, n=7;
		//int n=4;

		list.printList(head);
	//	list.printList(list.reverse(head,m,n));
		
		int cnt = list.countNodes(head);
	//	list.printList(list.reverse(head,(cnt/2+1),cnt));
		
		
		list.printList(list.deleteDuplicates(head));
	
	}

}

class Node {
	public int value;
	public Node next;

	Node(int value) {
		this.value = value;

	}
}

class LinkedList {

	private Node head;

	public void addToTheLast(Node node) {

		if (head == null) {
			head = node;
		} else {
			Node temp = head;
			while (temp.next != null)
				temp = temp.next;

			temp.next = node;
		}
	}

	public Node deleteDuplicates(Node head)
	{
		if(head==null || head.next == null)
			return head;
		
		Node first = head;
		Node second = head.next;
		
		while(second!=null)
		{
			if(second.value == first.value)
			{
				first.next = second.next;
				second = second.next;
			}
			else	
			{
			first = second;
			second = second.next;
			}
		}
		return head;
	}
	
	public int countNodes(Node head)
	{
		int cnt = 1;
		if( head==null)
		return 0;
		while(head.next!=null)
		{
			head = head.next;
			cnt++;
		}
		return cnt;
	}
	public void printList(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.format("%d ", temp.value);
			temp = temp.next;
		}
		System.out.println();
	}
	
	public Node reverse(Node head, int m, int n )
	{
		if(head == null)
			return head;
		if(m==n)
			return head;
		
		Node prev = null;
		Node first = new Node(0);
		Node second = new Node(0);
		
		int i=0;
		Node p = head;
		while(p!=null)
		{
			i++;
			if(i==m)
			{
				first.next = p;	
			}
			if(i==m-1)
			{
				prev = p;
			}
			if(i==n)
			{
				second.next = p.next;
				p.next = null;
			}
			p=p.next;
		}
		
		if(first.next==null)
			return head;
		
		Node p1 = first.next;
		Node p2 = p1.next;
		p1.next = second.next;
		while(p1!=null && p2!=null)
		{
			Node temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}
		
		if(prev!=null)
			prev.next = p1;
		else
			return p1;
		
		return head;
	}
}