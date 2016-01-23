
public class sqr_sum_diff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	int n=100;
	int diff=0;
	int sum=0; int sqr_sum=0;
	
	for(int i=1;i<=n;i++)
	{
		sqr_sum=sqr_sum+i*i;
	}
	
	sum=n*(n+1)/2;	
	diff=sum*sum-sqr_sum;
	System.out.println(diff);
	}
}
