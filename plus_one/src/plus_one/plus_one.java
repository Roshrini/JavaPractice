package plus_one;

public class plus_one {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num={0,9,9,9};
		int[] result=plusOne(num);
		for (int i=0;i<result.length;i++){
		System.out.print(""+(result[i]));
		}
	}
	public static int[] plusOne(int[] digits) {
		int len = digits.length;
		boolean flag = true; // flag if the digit needs to be changed
	 
		for (int i = len - 1; i >= 0; i--) {
			if (flag==true) {
				if (digits[i] == 9) {
					digits[i] = 0;
				} else {
					digits[i] = digits[i] + 1;
					flag = false;
				}
	 
				if (i == 0 && digits[i] == 0) {
				//	System.out.println("hi");
					int[] y = new int[len + 1];
					y[0] = 1;
					for (int j = 1; j <= len; j++) {
						y[j] = digits[j - 1];
					}
					digits = y;
				}
			}
		}
	 
		return digits;
	}

}
