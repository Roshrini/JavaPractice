package stock_profit;

public class MaximizeStackProfit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input[] = {100, 180, 260, 310, 40, 535, 695};
		String str = "rosh";
		
		System.out.println(onceProfit_n2(input));
		System.out.println(onceProfit_n(input));
		System.out.println(multiProfit(input));
	}
	
	public static int multiProfit(int[] input)
	{
		int max = 0;
	    for(int i=1; i<input.length; i++){
	        int profit = input[i]-input[i-1];
	        if(profit>0){
	        	max = max + profit;
	        }
	    }
	    return max;		
	}
	
	public static int onceProfit_n(int[] input)
	{
	/* 1) Maximum difference found so far (max_diff).
	   2) Minimum number visited so far (min_element).
	*/
		int min=input[0];
		int max=input[1]-input[0];
		int profit = 0;
		for(int i=0; i<input.length; i++)
		{
			profit = input[i]-min;
			if(profit>max)
				max=profit;
			if(input[i]<min)
				min=input[i];
		}
		return max;
	}
	
	public static int onceProfit_n2(int[] input)
	{
		int max=Integer.MIN_VALUE;
		int len = input.length;
		int profit = 0;
		for(int i=0; i<len; i++)
		{
			for(int j=i+1; j<len; j++)
			{
				profit = Math.abs(input[j]- input[i]);
				if(max<profit && input[j]>input[i])
					max=profit;
			}		
		}
		return max;
	}	
}
