import java.util.*;

public class merge_list {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		
		list2.add(5);
		list2.add(4);
		list2.add(3);
		list2.add(2);
		list2.add(1);
		
		int len1 = list1.size();
		int len2 = list2.size();
		int len3 = len1+len2;
		for(int i=0, j=len2-1; i<len1-1 ; i++,  j--)
		{
			
		}
	}

}
