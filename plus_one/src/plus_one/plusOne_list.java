package plus_one;

import java.util.ArrayList;

public class plusOne_list {

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<Integer>();
		num.add(0);
		num.add(2);
		num.add(5);
		num.add(6);
		num.add(8);
		num.add(1);
		num.add(1);
		num.add(2);
		num.add(4);
		num.add(5);
		//[2, 5, 6, 8, 6, 1, 2, 4, 5]; 
		ArrayList<Integer> result=plusOne(num);
		for (int i=0;i<result.size();i++){
		System.out.print(""+(result.get(i)));
		}
	}
		public static ArrayList<Integer> plusOne(ArrayList<Integer> num)
		{
			int len = num.size();
			boolean flag = true;
			for(int i=len-1; i>=0; i--)
			{
				if(flag==true)
				{
				if(num.get(i)==9)
					num.set(i, 0);
				else
				{
					num.set(i,num.get(i)+1);
					flag=false;
				}
			
			if(i==0 && num.get(i)==0)
			{
				ArrayList<Integer> op = new ArrayList<Integer>();
				op.add(0);
				for(int j=1; i<len-1; j++)
				{
					op.add(j,num.get(j));
				}
				num=op;
			}
				}
			}
		if(num.get(0)==0)
			num.remove(0);
			
			return num;
		}
	

}
