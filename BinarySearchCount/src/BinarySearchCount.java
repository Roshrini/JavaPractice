public class BinarySearchCount
{
    public BinarySearchCount()
    {
    }
 
    public static int countNumberOfOccurances(int[] a, int n, int k)
    {
        int firstOccurrence = binarySearchfirstOccurrence(a, n, 0, n, k);
        int lastOccurrence = binarySearchlastOccurrence(a, n, 0, n, k);
 
        if ((firstOccurrence == -1) && (lastOccurrence == -1))
        {
            return 0;
        }
        else if (firstOccurrence == lastOccurrence)
        {
            return 1;
        }
        else
        {
            return (lastOccurrence - firstOccurrence + 1);
        }
    }
 
    public static int binarySearchfirstOccurrence(int[] a, int n, int low,
        int high, int k)
    {
        if (high >= low)
        {
            int mid = (low + high) / 2;
 
            if (((mid == 0) && (a[mid] == k)) ||
                    ((a[mid] == k) && (a[mid - 1] < k)))
            {
                return mid;
            }
            // Favor left half of the array
            else if (a[mid] >= k)
            {
                return binarySearchfirstOccurrence(a, n, low, mid - 1, k);
            }
            else
            {
                return binarySearchfirstOccurrence(a, n, mid + 1, high, k);
            }
        }
 
        return -1;
    }
 
    public static int binarySearchlastOccurrence(int[] a, int n, int low,
        int high, int k)
    {
        if (high >= low)
        {
            int mid = (low + high) / 2;
 
            if (((mid == n) && (a[mid] == k)) ||
                    ((a[mid] == k) && (a[mid + 1] > k)))
            {
                return mid;
            }
            // Favor right half of the array
            else if (a[mid] <= k)
            {
                return binarySearchlastOccurrence(a, n, mid + 1, high, k);
            }
            else
            {
                return binarySearchlastOccurrence(a, n, low, mid - 1, k);
            }
        }
 
        return -1;
    }
 
    public static void main(String[] args)
    {
        //  Input Array
        int[] a = { 1, 2, 2, 3, 3, 3, 3, 5, 6, 6};
 
        //  k - Element to be found
        int k = 6;
 
        //  n - Size of the array
        int n = a.length;
        System.out.println(countNumberOfOccurances(a, n - 1, k));
    }
}