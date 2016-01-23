import java.util.*;

public class isPermutationPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] str_list = {"civic", "ivicc", "civil"};
		
		for(int i=0;i<str_list.length;i++)
		{
			HashMap<Character,Integer> map = new HashMap<Character,Integer>();
			char[] str_arr = str_list[i].toCharArray();
			for(char key: str_arr){
				   if(map.containsKey(key)){
				    Integer value = map.get(key);
				    value++;
				    map.put(key, value);
				   }else{
				    map.put(key,1);
				   }
				  }
//			for(Map.Entry<Character,Integer> entry : map.entrySet() )
//				System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			System.out.println(checkPalindrome(str_arr,map));
		}
		
		
	}
	public static boolean checkPalindrome(char[]array, HashMap<Character,Integer> map){
		 
		  /*for(Character key: map.keySet()){
		   System.out.println("key: "+key+" value:"+map.get(key));
		  }*/
		  if(array.length % 2 == 0){ // check for even length
		   int flag;
		   for(Character key: map.keySet()){
		    flag = map.get(key);
		  //  System.out.println("rosh "+flag);
		    if(flag % 2 != 0){
		     return false;
		    }
		   }
		   return true;
		  }
		  else{ // check for odd length
		   boolean trigger = false;
		   for(Character key: map.keySet()){
		   int flag = map.get(key);
		   System.out.println("key: "+key+" value:"+map.get(key));
		    if(flag % 2 != 0){
		     if(trigger == false){
		      trigger = true;
		     }else if(trigger == true){
		      return false;
		     }
		    }
		   }return true;
		  }
	}
}