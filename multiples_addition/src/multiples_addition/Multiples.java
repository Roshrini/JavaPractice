package multiples_addition;

public class Multiples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a=0;
		for(int i=0;i<1000;i++)
		{
		if(i%3==0 || i%5==0)
		{
			a=a+i;
			
		}
		}
System.out.println(a);
	}

}
