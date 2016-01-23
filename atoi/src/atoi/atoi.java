package atoi;
import java.io.ObjectInputStream.GetField;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class atoi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char num1[] = {'1','2'};
		char num2[] = {'3','4'};
		String n1="+266";
		String n2="734";
		int number1;
		int number2;
		System.out.println(atoi(n1));
	//	System.out.println(atoi(n2));
		//System.out.println(atoi(n1)+atoi(n2));

	}
	
	public static int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
	 
		// trim white spaces
		str = str.trim();
		char[] c =new char[10];
		c=str.toCharArray();
		char flag = '+';
	 
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		
	//	System.out.println("i"+i+"  "+(int)'0');
		// use double to store result
		double result = 0;
	
	 
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			System.out.println("hey "+str.charAt(i));
			result = result * 10 + (str.charAt(i) - '0');
			
			i++;
		}
	 
		if (flag == '-')
			result = -result;
	 
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
	 
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
	 
		return (int) result;
	}
	

}
