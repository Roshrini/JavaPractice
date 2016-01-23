import java.util.*;
public class isPerPali {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] str_list = {"cici","ciciv","civil","civic","rosh","baba","abcdedcba"};
		
		for(int i=0; i<str_list.length;i++)
		{
			HashMap<Character,Integer> map = new HashMap<Character,Integer>();
			char str_arr[] = str_list[i].toCharArray();
			for(int j=0; j <str_arr.length;j++)
			{
				if(map.containsKey(str_arr[j]))
				{
					int val = map.get(str_arr[j]);
					val++;
					map.put(str_arr[j], val);
				}
				else
				{
					map.put(str_arr[j], 1);
				}
			}
			System.out.println(str_list[i]+"   "+isPalindrome(map,str_arr));
		}

	}
	
	public static boolean isPalindrome(HashMap<Character,Integer> map,char[] array)
	{
		boolean flag= false;
		
		if(array.length %2 == 0)
		{
			for(char c: map.keySet())
				{
					if(map.get(c)%2 != 0)
						return false;
				}
			return true;
		}
		else
		{
			boolean trigger = false;
			for(char c: map.keySet())
			{
				if(map.get(c)%2 != 0){
					if(trigger == false)
						trigger = true;
				else if(trigger == true)
						return false;
				}
			}
			return true;
		}
	}

}
