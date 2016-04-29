
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class shuffle_reverse_merge {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    //    Scanner in = new Scanner(System.in);
      //  String input = in.next().trim();
        String input = "aaeeacacbabeae";
        int len = input.length()/2;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char[] arr = input.toCharArray();
        String A = "";
        String B = "";
        for(int i=0; i<input.length(); i++)
        {
            if(map.containsKey(arr[i]))
            {
                int c = map.get(arr[i]);
                map.put(arr[i],c+1);
            }
            else
                map.put(arr[i],1);
        }
         
        for(Map.Entry<Character, Integer> entry : map.entrySet())
            {            
             A = A + entry.getKey()+"";
             B = B + entry.getKey()+"";
            StringBuilder builderA = new StringBuilder(A);
            StringBuilder builderB = new StringBuilder(B);
            for (int i = 0; i < entry.getValue()/2-1; i++) {
                builderA.append(entry.getKey()+"");
                builderB.append(entry.getKey()+"");
                }
            A = builderA.toString();
            B = builderB.toString();
                       
        } 
       //  System.out.println(A);
         //System.out.println(B);
         char[] op = A.toCharArray();
         Arrays.sort(op);
        System.out.println(String.valueOf(op));
    }
}



