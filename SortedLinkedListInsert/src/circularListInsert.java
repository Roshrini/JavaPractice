
public class circularListInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  LinkedList list=new LinkedList();
		/* as we have to insert the element in a sorted list 
	    we are inserting the element in sorted order  */
	    list.insert_at_end(1);
	    list.insert_at_end(3);
	    list.insert_at_end(4);
	    list.insert_at_end(5);
	    System.out.println("\nTraversing the linked List");
	    list.display();
	    System.out.println("\nTraversing the linked List");
	    list.insert_in_sorted_list(1);
	    list.insert_in_sorted_list(2);
	    list.insert_in_sorted_list(2);
	    list.insert_in_sorted_list(20);
	    list.insert_in_sorted_list(9);
	    list.insert_in_sorted_list(0);
	    list.display();

	}

}
class Node 
{
    int data ;
    Node next;
    Node(int d)
    {
        data =d;
        next=null;
    }
}
class LinkedList
{
    Node root=null;
    Node newnode;
    Node temp;
    
    /*function to insert the element at the end */
    public void insert_at_end(int d)
    {
    newnode = new Node(d);
    if(root==null)
    {
        root=newnode;
        newnode.next=root;
    }
    else 
    {
        temp=root;
        while(temp.next!=root)
        {
            temp=temp.next;
        }
        temp.next=newnode;
        newnode.next=root;
    }
    }
    
  //function to insert the element in sorted list
    public void insert_in_sorted_list(int num)
     {  
    	newnode=new Node(num);
        temp =root;
        // if root is null
        if(root==null)
        {
           root=newnode;
           newnode.next=root;
         
        }
        // if element to be added is less than root node
          /*  then we will reach till the last node and then 
            add newnode after last node nad make newnode as root node */
        if(temp.data >=newnode.data)
        {
            while(temp.next!=root )
            temp=temp.next;
           
           temp.next=newnode;
           newnode.next=root;
           root=newnode;
        }
        // if element to be added is greater than root node 
         /* then we will locate the node after which node is neede to added         
         */
         else{
            while(temp.next!=root && temp.next.data<newnode.data )
            temp=temp.next;
            
            newnode.next=temp.next;
            temp.next=newnode;
            }
     }
    
    /*function to display the list   */
    public void display()
    {
        Node start=root;
        
        if(start!=null)
        {
        do
        {
            System.out.print(start.data+"->");
            start=start.next;
        }
        while(start!=root);
        }
    }
}