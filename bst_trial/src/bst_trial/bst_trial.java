package bst_trial;

class Node
{
	Node left,right;
	int val;
	
	Node()
	{
		left = null;
		right = null;
		val = 0;
	}
	Node(int x)
	{
		left = null;
		right = null;
		val = x;
	}
	public void setleft(Node n)
	{
		left = n;
	}
	public void setright(Node n)
	{
		right = n;
	}
	public Node getleft()
	{
		return left;
	}
	public Node getright()
	{
		return right;
	}
	public void setdata(int x)
	{
		val = x;
	}
	public int getdata()
	{
		return val;
	}
}

public class bst_trial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n= new Node();

	}

}
