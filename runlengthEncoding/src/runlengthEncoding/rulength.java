package runlengthEncoding;

public class rulength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "aabab";
		System.out.println("output "+runlength(input));
	}

public static String runlength(String input)
{
    String output = "";
    if(input.length() == 0)
        return output;
    
    if(input.length() == 1)
        return 1+"x"+input.charAt(0);
     
    int cnt =1; 
    char alpha1 = (char) 0;
    char alpha2 = (char) 0;  
    for(int i=0; i<input.length()-1;i++)
    {
        alpha1 = input.charAt(i);
        alpha2 = input.charAt(i+1);
        if(alpha1==alpha2)
        {
        cnt++;
        }
        else
        {
        output = output+cnt+"x"+alpha1;
        cnt = 1;
        }
        
    }
    if(alpha1==alpha2)
        output = output+cnt+"x"+alpha1;
//     if(alpha1!=input.charAt(input.length()-1))
//            output = output + cnt+"x"+alpha2;
    return output;
}
}