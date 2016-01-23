	package hashmap_ex;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
class BSTNode
{
    BSTNode left, right;
    int data;

    /* Constructor */
    public BSTNode()
    {
        left = null;
        right = null;
        data = 0;
    }
    /* Constructor */
    public BSTNode(int n)
    {
        left = null;
        right = null;
        data = n;
    }
}

//public class Solution {
//	int[] nums={1,2,2,3,4};
//    public boolean containsDuplicate(int[] nums) {
//    	 HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
//    	 boolean status=true;
//         
//         int val;
//        for(int i=0;i<nums.length;i++)
//        {
//        	int n = nums[i];           
//            if (m.containsKey(n)){
//                val = m.get(n);
//                m.put(n,val+1);
//            }
//            else
//            {
//                m.put(n,1);
//            }
//        }
//        for(int j=0 ; j<m.size(); j++)
//        {
//        if(m.get(j) > 1)
//        System.out.println("Duplicate");
//        }
//        return false;
//    }
//    
//}

public class hashmap_count {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] hehe={1,2,3,4,5,1,2,3,4,5,6,7,8};
		int val;
		List<Integer> l1=new LinkedList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		
		List<Integer> l2=new LinkedList<Integer>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		
		System.out.println(l1);
		String str1="";
		for(int i=0;i<l1.size();i++)
		{
			str1=str1+l1.get(i);
		}
		String str2="";
		for(int i=0;i<l2.size();i++)
		{
			str2=str2+l2.get(i);
		}
		
		System.out.println(Integer.parseInt(str1)+Integer.parseInt(str2));
		Hashtable<Integer,String> hi;
		hi=new Hashtable <Integer,String>();
		
		HashMap<Integer,Integer> hello= new HashMap<Integer,Integer>();
	//	hello.put(1, "ge");
		for(int i=0; i<hehe.length;i++){
			
	//	hello.put(hehe[i], 1);
		if(hello.containsKey(hehe[i])){
			val=hello.get(hehe[i]);
			hello.put(hehe[i], val+1);
		}
		else{
			hello.put(hehe[i], 1);
		}
		
		
		}
		System.out.println("output"+hello);
		

	}

}
