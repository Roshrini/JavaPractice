import java.util.*;

public class threeSum {
	public static void main(String args[])
	{
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		int[] arr = {-1,0,1,2,-1,-4};
		result = threeSum(arr);
		
		for(int i=0; i<result.size(); i++)
		{
			System.out.println(result.get(i));
		}
	}
	
    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num.length < 3) return result;
        
        Arrays.sort(num);
        
        for(int i = 0; i<num.length-2; i++){
            if(i==0 || num[i]>num[i-1]){
                int negate = -num[i];
                
                int start = i+1;
                int end = num.length - 1;
                System.out.println(negate);
                while(start<end){
                	System.out.println(start + " " + end);
                    if(num[start]+num[end] == negate){
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[start]);
                        temp.add(num[end]);
                        result.add(temp);
                        
                        start++;
                        end--;
                        
                        //make sure no duplicates
                        while(start < end && num[end] == num[end+1]){
                            end--;
                        }
                        while(start < end && num[start] == num[start-1]){
                            start++;
                        }
                    }else if(num[start]+num[end] < negate){
                        start++;
                    }else{
                        end--;
                    }
                }
            }
        }
        return result;
    }
}