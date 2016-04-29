public class FindElementThatAppearOnceRestAllAppearThrice {
 
 public static void main(String[] args) {
  int arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3};
  System.out.println(findElementThatAppearOnce(arr));
 }
 
 private static int findElementThatAppearOnce(int arr[]){
 
  int firstTimeOccurence = 0;
  int secondTimeOccurence = 0;
 
  for (int i=0; i < arr.length; i++){
   secondTimeOccurence =  secondTimeOccurence | (firstTimeOccurence & arr[i]);
   System.out.println(secondTimeOccurence+"  "+firstTimeOccurence+"  "+arr[i]+"  "+(firstTimeOccurence & arr[i]));
   firstTimeOccurence = firstTimeOccurence ^ arr[i]; 
   int neutraliser = ~(firstTimeOccurence & secondTimeOccurence);
   firstTimeOccurence = firstTimeOccurence & neutraliser;
   secondTimeOccurence = secondTimeOccurence & neutraliser;
  }
  return firstTimeOccurence;
 }
}
