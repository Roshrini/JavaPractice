package hashmap_simple;
import java.util.*;

public class hashmap_sample {
   public static void main(String args[]) {
//   // create hash map
//   HashMap newmap = new HashMap();
//      
//   // populate hash map
//   newmap.put(1, "tutorials");
//   newmap.put(2, "point");
//   newmap.put(3, "is best");
//      
//   // create set view for the map
//   Set set=newmap.entrySet();
//   
//      
//   // check set values
//   System.out.println("Set values: " + set);
   
   HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
  int[] nums = {1,2,3,4,5,2,3,4,8};
   boolean status=false;
   int val;
   if(nums.length == 0)
      System.out.println("false");
  for(int i=0;i<nums.length;i++)
  {
  	int n = nums[i];           
      if (m.containsKey(n)){
          val = m.get(n);
          m.put(n,val+1);
          System.out.println("true");
      //    break;
      }
      else
      {
          m.put(n,1);
          
      }	  
  }
  HashMap<String,String> str = new HashMap<String,String>();
		  str.put("deer","d2r");
			str.put("deer","");
  
//  for(Map.Entry<Integer, Integer> entry : m.entrySet())
//  {
//	  System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
//}
  
  for(Map.Entry<String, String> entry : str.entrySet())
  {
	  System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
}
//  
//  for(Map.Entry<Integer,Integer> entry : m.entrySet())
//	  System.out.println(entry.getValue());
  
  
  Iterator<Map.Entry<Integer, Integer>> iterator = m.entrySet().iterator();
  while(iterator.hasNext()){
     Map.Entry<Integer, Integer> entry = iterator.next();
     System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
     iterator.remove(); // right way to remove entries from Map, 
                        // avoids ConcurrentModificationException
  }
  }    
}