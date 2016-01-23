import java.util.Stack;


public class validExpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String input = "(()))(";
		//String input = "(()())";
		String input = "))((";
		
		System.out.println(isValid(input));
		System.out.println(isValidStack(input));

	}
	
	public static boolean isValid(String input)
	{
		int n=0;
		for(int i=0; i<input.length(); i++)
		{
			
			if(input.charAt(i)=='(')
				n++;
			else if(input.charAt(i)==')')
			{
				if(n==0)
					return false;
				else
					n--;
			}
		//	System.out.println(n);
		}
		return (n==0);
	}
	
	public static boolean isValidStack(String input)
	{
		Stack<Character> stack = new Stack<Character>();
		for(int i=0; i<input.length(); i++)
		{
			char e = input.charAt(i);
			if( e=='(')
			{
				stack.push(e);
			}
			else {
                if (!stack.isEmpty()) {
                    int k = stack.pop();
                    if (k != '(') {
                        return false;
                    }
                } else {
                    return false;
                }
		}
		}
		return (stack.isEmpty());
	}
}
