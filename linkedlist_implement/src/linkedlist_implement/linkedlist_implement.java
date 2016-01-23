package linkedlist_implement;

public class linkedlist_implement {
	 
    public static void main(String[] args) {
        CrunchifyLinkedList lList = new CrunchifyLinkedList();
 
        // add elements to LinkedList
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");
 
        /*
         * Please note that primitive values can not be added into LinkedList
         * directly. They must be converted to their corresponding wrapper
         * class.
         */
 
        System.out.println("lList - print linkedlist: " + lList);
        System.out.println("lList.size() - print linkedlist size: " + lList.size());
        System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
        System.out.println("lList.remove(2) - remove 2nd element: " + lList.remove(2));
        System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
        System.out.println("lList.size() - print linkedlist size: " + lList.size());
        System.out.println("lList - print linkedlist: " + lList);
    }
}
 
class CrunchifyLinkedList {
    // reference to the head node.
    private Node head;
    private int listCount;
 
    // LinkedList constructor
    public CrunchifyLinkedList() {
        // this is an empty list, so the reference to the head node
        // is set to a new node with no data
        head = new Node(null);
        listCount = 0;
    }
 
    public void add(Object data)
    // appends the specified element to the end of this list.
    {
        Node crunchifyTemp = new Node(data);
        Node crunchifyCurrent = head;
        // starting at the head node, crawl to the end of the list
        while (crunchifyCurrent.getNext() != null) {
            crunchifyCurrent = crunchifyCurrent.getNext();
        }
        // the last node's "next" reference set to our new node
        crunchifyCurrent.setNext(crunchifyTemp);
        listCount++;// increment the number of elements variable
    }
 
    public void add(Object data, int index)
    // inserts the specified element at the specified position in this list
    {
        Node crunchifyTemp = new Node(data);
        Node crunchifyCurrent = head;
        // crawl to the requested index or the last element in the list,
        // whichever comes first
        for (int i = 1; i < index && crunchifyCurrent.getNext() != null; i++) {
            crunchifyCurrent = crunchifyCurrent.getNext();
        }
        // set the new node's next-node reference to this node's next-node
        // reference
        crunchifyTemp.setNext(crunchifyCurrent.getNext());
        // now set this node's next-node reference to the new node
        crunchifyCurrent.setNext(crunchifyTemp);
        listCount++;// increment the number of elements variable
    }
 
    public Object get(int index)
    // returns the element at the specified position in this list.
    {
        // index must be 1 or higher
        if (index <= 0)
            return null;
 
        Node crunchifyCurrent = head.getNext();
        for (int i = 1; i < index; i++) {
            if (crunchifyCurrent.getNext() == null)
                return null;
 
            crunchifyCurrent = crunchifyCurrent.getNext();
        }
        return crunchifyCurrent.getData();
    }
 
    public boolean remove(int index)
    // removes the element at the specified position in this list.
    {
        // if the index is out of range, exit
        if (index < 1 || index > size())
            return false;
 
        Node crunchifyCurrent = head;
        for (int i = 1; i < index; i++) {
            if (crunchifyCurrent.getNext() == null)
                return false;
 
            crunchifyCurrent = crunchifyCurrent.getNext();
        }
        crunchifyCurrent.setNext(crunchifyCurrent.getNext().getNext());
        listCount--; // decrement the number of elements variable
        return true;
    }
 
    public int size()
    // returns the number of elements in this list.
    {
        return listCount;
    }
 
    public String toString() {
        Node crunchifyCurrent = head.getNext();
        String output = "";
        while (crunchifyCurrent != null) {
            output += "[" + crunchifyCurrent.getData().toString() + "]";
            crunchifyCurrent = crunchifyCurrent.getNext();
        }
        return output;
    }
 
    private class Node {
        // reference to the next node in the chain,
        // or null if there isn't one.
        Node next;
        // data carried by this node.
        // could be of any type you need.
        Object data;
 
        // Node constructor
        public Node(Object dataValue) {
            next = null;
            data = dataValue;
        }
 
        // another Node constructor if we want to
        // specify the node to point to.
        public Node(Object dataValue, Node nextValue) {
            next = nextValue;
            data = dataValue;
        }
 
        // these methods should be self-explanatory
        public Object getData() {
            return data;
        }
 
        public void setData(Object dataValue) {
            data = dataValue;
        }
 
        public Node getNext() {
            return next;
        }
 
        public void setNext(Node nextValue) {
            next = nextValue;
        }
    }
}