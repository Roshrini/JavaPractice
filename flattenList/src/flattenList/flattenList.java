package flattenList;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class flattenList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        // List of strings
        LinkedList stringList = new LinkedList();
        for (Integer i = 0; i < 10; i++) {
                stringList.add(i.toString());
        }

        // List of integers
        LinkedList intList = new LinkedList();
        for (Integer i = 10; i < 20; i++) {
                intList.add(i);
        }

        // Nested Lists
        LinkedList nestedList1 = new LinkedList();
        LinkedList nestedList2 = new LinkedList();
        LinkedList nestedList3 = new LinkedList();

        nestedList3.add("Nested String 1");
        nestedList3.add("Nested String 2");
        nestedList2.add(nestedList3);
        nestedList1.add(nestedList2);

        LinkedList bigList = new LinkedList();

        bigList.add("First");
        bigList.add(stringList);
        bigList.add("Third");
        bigList.add(intList);
        bigList.add("Fifth");
        bigList.add(nestedList1);
        bigList.add("Seventh");

     // List flattenedList = flattenList(bigList);
        List flattenedList = flattenListNoRecursion(bigList);

      printList(bigList);
      System.out.println("Roshani");
      printList(flattenedList);

	}
	private static List flattenListNoRecursion(LinkedList inList) {
		// TODO Auto-generated method stub
		List tempList = null;
        // Clone the input list to newList
        List newList = new LinkedList();
        newList.addAll(inList);

        ListIterator iterator = newList.listIterator();

        int currentPosition = 0;

        while (iterator.hasNext()) {
                Object i = iterator.next();
                if (!(i instanceof List)) {
                        // If it's not a list, advance the position.  Don't advance position if this IS a list.
                        currentPosition++;
                } else {
                        // If the current item is a list, save it to a temp var.
                        tempList = (List) i;

                        // Delete the list from the list
                        iterator.remove();

                        // Add each item from the temp list to the master list at the same position the sublist was removed.
                        for (Object obj : tempList) {
                                iterator.add(obj);
                        }

                        // reset the iterator to re-walk the list that was just inserted (within the master) to check for more lists.
                        iterator = newList.listIterator(currentPosition);
                }
        }
        return newList;		
	}
	private static List flattenList(LinkedList bigList) {
		// TODO Auto-generated method stub
		List flatlist = new LinkedList();
		for(Object l: bigList)
		{
			if(l instanceof List)
			{
				flatlist.addAll(flattenList((LinkedList)l));
			}
			else
				flatlist.add(l);
		}
		
		return flatlist;
	}
	public static void printList(List list) {
        int i = 0;
        for (Object item : list) {
                System.out.println("List #"+i +": "+item);
                i++;
        }
}
}
