package reverseWords_withoutsplit;

public class reverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str ="The quick     brown Fox  ";
		char rev;
        int k=0;        
        String op="";
        for(int i=0;i<str.length();i++)
        {           
        	
            if(str.charAt(i)==32 ||i==str.length()-1)
            {
                for(int j=i;j>=k;j--)
                {
                	if(j>=1){
                    rev=str.charAt(j-1);
                    op=op+rev;
                	}
                   // System.out.print(rev);
                }
           //    op =op+" ";
            	
                k=i;
            	
                continue;
            }             
        }
        
        System.out.println(op);
	}

}
