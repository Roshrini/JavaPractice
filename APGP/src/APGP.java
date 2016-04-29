
public class APGP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A	=	1;
		int	D	=	1;
		int	B	=	1;
		int	R	=	2;
		int	Lim	=	4;
		System.out.println(countSeriesTerms(A,	D,	B,	R,	Lim));

	}
	public static int countSeriesTerms(int A,int D,int B,int R,int lim)
	{
		int aval=A, bval=B;
		int cnt=0;
		while(aval<=lim || bval<=lim)
		{
			if(aval==bval)
			{
			aval = aval+D;
			bval = bval*R;
			cnt++;
			}
			else if(aval<bval)
				aval = aval+D;
			else
				bval = bval*R;
		}
		return cnt;
	}
}
