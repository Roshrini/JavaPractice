import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       // Scanner in = new Scanner(System.in);
      //  String entry = in.next();
        String entry = "eggegg";
        int[] frequency = new int[26];
        int uniqueElement = 0;
        for(int i =0; i < entry.length(); i++){
            char currentChar = entry.charAt(i);
            if(frequency[currentChar - 'a'] == 0){
                uniqueElement++;
            }
            frequency[currentChar - 'a']++;
        }
        //Halve the frequency of each character to find the frequency the characters in the answer
        for(int i =0; i<26; i++){
            frequency[i] = frequency[i]/2;
            System.out.println(frequency[i]+"  "+uniqueElement);
        }
        int lastIndex = entry.length();
        StringBuilder result = new StringBuilder(lastIndex/2);
        while(uniqueElement > 0){
            //find the smallest window which have elemnents of frequency as subsequence
            int[] tempFrequency = frequency.clone();
            int tempUniqueElement = uniqueElement;
            int windowSize = 0;
            
            for(int i=0; i < entry.length(); i++){
                char currentChar = entry.charAt(i);
                tempFrequency[currentChar - 'a']--;
                if(tempFrequency[currentChar - 'a'] == 0) tempUniqueElement--;
                if(tempUniqueElement == 0) break;
                windowSize++;
            }
            Character minCharacter = null;
            for(int i= lastIndex - 1; i >= windowSize; i--){
                if(frequency[entry.charAt(i) - 'a'] == 0){
                    continue;
                }
                if(minCharacter == null || minCharacter > entry.charAt(i)){
                    minCharacter = entry.charAt(i);
                    lastIndex = i;
                }
            }
            result.append(minCharacter);
            frequency[minCharacter - 'a']--;
            if(frequency[minCharacter - 'a'] == 0){
                uniqueElement--;
            }
        }
        System.out.println(result.toString());
    }
}
