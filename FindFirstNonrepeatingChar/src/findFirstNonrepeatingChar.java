import java.util.HashMap;

public class findFirstNonrepeatingChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "ppii";
		System.out.println(find(str));
	}
	public static Character find(String input)
	{
		char c = '\0';
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		char[] arr = input.toCharArray();
		for(int i=0;i<arr.length;i++)
		{
			if(map.containsKey(arr[i]))
				map.put(arr[i],map.get(arr[i])+1);
			else
				map.put(arr[i], 1);
		}
		for(int i=0;i<arr[i];i++)
		{
			if(map.get(arr[i])==1)
				return arr[i];
			else
				return 'x';
		}
		return c;
	}

}
