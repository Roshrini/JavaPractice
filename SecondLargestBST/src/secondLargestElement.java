
public class secondLargestElement {
static int c =0;
	static class Node{
		private Node left;
		private Node right;
		private int val;
		public Node(int v)
		{
			left = null;
			right = null;
			this.val = v;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Node root = new Node(50);
	//	insert1(root, new Node(10));
		insert1(root,30);
		insert1(root,20);
		insert1(root,40);
		insert1(root,70);
		insert1(root,60);
		insert1(root,80);
	//	inorder(root);
		System.out.println();
		System.out.println(secondLargestHelper(root));
	}
	 public static void insert1(Node root,int in)
     {
         root = insert(root, in);
     }
	public static Node insert(Node node, int in)
	{
		if(node == null)
			node = new Node(in);
		else{
		if( node.val> in)
			node.left = insert(node.left, in);
		else if(node.val< in)
			node.right = insert(node.right, in);
		}
	//	System.out.println(in.val);
		return node;
	}
	 public static void inorder(Node root)
     {
         print(root);
     }
	public static void print(Node root)
	{
		if(root!=null){
		print(root.left);
		System.out.println(root.val);
		print(root.right);
		}
	}
	
//	public static void secondLargest(Node root)
//	{
//	//	int c= 0;
//		secondLargestHelper(root,c);
//	}
//	
//	public static void secondLargestHelper(Node root, int c)
//	{
//		if(root == null || c >= 2)
//			return;
//			
//		
//		secondLargestHelper(root.right,c);
//		c++;
//		if(c==2){
//			System.out.println(root.val);
//			return;
//		}
//		secondLargestHelper (root.left,c);
//		
//	}
	
	public static int Largest(Node root)
	{
		if(root.right !=null)
			Largest(root.right);
		return root.val;
		//secondLargestHelper(root);
	}
	
	public static int secondLargestHelper(Node root)
	{
		if(root==null)
			return -1;
		
		if(root.left!=null && root.right==null)
			return Largest(root.left);
		
		if(root.right!=null && root.right.left==null && root.right.right ==null){
			return root.val;
		}
		
		return secondLargestHelper(root.right);
	}
}