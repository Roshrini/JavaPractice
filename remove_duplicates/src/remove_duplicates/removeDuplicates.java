package remove_duplicates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

class LinkedlistNode
{
	int val;
	LinkedlistNode next;
	
	LinkedlistNode(int x)
	{
		val=x;
	}
}

public class removeDuplicates {

	public static void removeDupli(LinkedlistNode head)
	{
		HashMap<Integer,Integer> values= new HashMap<Integer,Integer>();
	//	HashSet<Integer> set = new HashSet<Integer>(head);

		if(head==null)
				return;
		else
		{
			while(head!=null)
			{
				int i=0;
				if(values.containsKey(head.val))
				{
					i = values.get(head.val);
					values.put(head.val,i+1);
				}
				else
				{
					values.put(head.val, 1);
				}
				head = head.next;
			}
		}
		
		for (Integer key : values.keySet()) {
			if (values.get(key)==1){
			   System.out.println("------------------------------------------------");
			   System.out.println("key: " + key + " value: " + values.get(key));
			}
			}

	}
	 public static void removeDuplicates(LinkedList list) {
	        Map<Integer, Boolean> buffer = new HashMap<Integer, Boolean>();
	        LinkedlistNode curr = (LinkedlistNode) list.get(0);
	        LinkedlistNode next;
	        while (curr.next != null) {
	            next = curr.next;
	            if(buffer.get(next.val) != null) {
	                // already seen it, so delete
	                curr.next = next.next;
	            } else {
	                buffer.put(next.val, true);
	                curr = curr.next;
	            }
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedlistNode one = new LinkedlistNode(1);
		LinkedlistNode two  = new LinkedlistNode(2);
		LinkedlistNode three  = new LinkedlistNode(2);
		LinkedlistNode four  = new LinkedlistNode(4);
		LinkedlistNode five  = new LinkedlistNode(5);
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = null;
		
		removeDupli(one);
		System.out.println(one.val+" "+two.val+" "+three.val+" "+four.val+" "+five.val);
		
		
		LinkedList list = new LinkedList();
        list.add(3);
        list.add(29);
        list.add(3);
        list.add(12);
        list.add(29);
        list.add(29);
        list.add(23);
        list.add(34);
        list.add(3);

       // list.print();
        removeDuplicates(list);
	}

}
