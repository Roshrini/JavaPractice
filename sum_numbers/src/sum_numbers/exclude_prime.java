package sum_numbers;

public class exclude_prime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int n =100;
		int[] input = {10,20,100,33,44,23};
		int sum=0;
		for (int i=0; i<input.length;i++)
		{
			if(!isPrime(input[i])){
			sum = sum + input[i];
			System.out.println(input[i]);
			}
			
		}
		System.out.println(sum);

	}
	public static boolean isPrime(int num)
	{
		for(int i=2;i<=num/2;i++)
		{
			if(num%i==0)
				return false;
		}
		return true;
	}

}
