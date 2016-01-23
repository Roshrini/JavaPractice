
public class LIS_Dynamic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {10,22,9,33,21,50,41,60,80};
		int n = input.length;
		System.out.println("output "+LIS(input,n));
	}
	
	public static int LIS(int[] input, int n)
	{
		int l[] = new int[n]; // storing lislength of each element as last in subsequence
		l[0] = 0;
		for (int i = 0; i < n; i++) {
			int lisPrev = 0;
			for (int j = 0; j < i; j++) {
				if (input[i] > input[j]) {
					lisPrev = Math.max(lisPrev, l[j]);
					System.out.println(lisPrev);
				}
			}
			l[i] = lisPrev+1;
		}
		int maxLis = l[0];
		for (int lis : l) {
			maxLis = Math.max(lis, maxLis);
		}
		return maxLis;
	}
}
