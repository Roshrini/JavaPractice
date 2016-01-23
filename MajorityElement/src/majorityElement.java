
public class majorityElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,1,1,3,3,2,2,3,3,3,2,3,3};
		int maj = arr[0];
		int cnt = 1;
		
		for(int i=0; i<arr.length-1; i++)
		{
			if(maj==arr[i+1])
				cnt++;
			else
			{
				cnt--;
				if(cnt==0)
					maj = arr[i];
			}
		}
		
		System.out.println(maj);
	}

}
