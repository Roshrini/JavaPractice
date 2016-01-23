package maxProfit;

public class maxProfit {

	public static void main(String[] args) {		
		int[] cost = {39,10,20,40,3};
		//int[] cost = {2, 3, 10, 6, 4, 8, 1};
		int profit = findProfit(cost);
		System.out.println("Profit "+profit);
		System.out.println("Profit "+maxDiff(cost,cost.length));

	}
	public static int findProfit(int[] cost)
	{
		int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
		if(cost.length<=1)
			return 0;
		for(int i=0; i<cost.length; i++)
		{
			if(max<cost[i])
				max = cost[i];
			if(min > cost[i])
				min = cost[i];
		}
		return (max-min);
	}

	public static int maxDiff (int arr[], int n)
	{
	    // Initialize diff, current sum and max sum
	    int diff = arr[1]-arr[0];
	    int curr_sum = diff;
	    int max_sum = curr_sum;
	 
	    for(int i=1; i<n-1; i++)
	    {
	        // Calculate current diff
	        diff = arr[i+1]-arr[i];
	 
	        // Calculate current sum
	        if (curr_sum > 0)
	           curr_sum += diff;
	        else
	           curr_sum = diff;
	        System.out.println(curr_sum+" "+max_sum);
	        // Update max sum, if needed
	        if (curr_sum > max_sum)
	           max_sum = curr_sum;
	    }
	  
	    return max_sum;
	}
	
	  
    public static int diff(int arr[], int n)
    {
    	int d = arr[1]-arr[0];
    	int max;
    	int cur = max = d;
    	
    	for(int i=0;i<n;i++)
    	{
    		d = arr[i+1]-arr[i];
    		if(cur>0)
    			cur =cur+ d;
    		else
    		{
    			cur = d;
    		}
    		if(cur>max)
    			max=cur;
    	}
    	return max;
    //	return 0;
    }
    
    public static int differ(int arr[], int n)
    {
    	int diff = arr[1]-arr[0];
    	int max;
    	int cur = max = diff;
    	for(int i=1; i <n; i++)
    	{
    		int d = arr[i+1]-arr[i];
    		if(cur>0)
    		{
    			cur = cur + d;
    		}
    		else
    			cur =d;
    		if(cur>max)
    			max=cur;
    			
    	}
    	return max;
    }
 
}
