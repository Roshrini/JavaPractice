import java.util.HashMap;
public class twoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//int n=0;
			int[] input = {1, 4, 45, -6, 10, -8};
			int[] op = Twosum_map(input,-14);
			for(int i=0; i<op.length; i++)
			{
				System.out.println(op[i]);
			}
			Twosum_space1(input,-14);
	}
	
	public static int[] Twosum_map(int[] input, int n)
	{
		int[] output = new int[2];
		HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
		
		if(input.length==0)
			return output;
		
		for(int i=0; i<input.length; i++)
		{
			if(map.containsKey(input[i]))
			{
				int j = map.get(input[i]);
				output[0] = input[j];
				output[1] = input[i];
				break;
			}
			else
			{
				map.put(n-input[i],i);
			}
		}
		return output;
	}
	
	public static int[] Twosum_space1(int[] input, int n)
	{
		
		return input;
	}
}
