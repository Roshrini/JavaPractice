import java.util.Stack;

/**
 * Sorts a stack using operations push,pop,peek and isEmpty
 * 
 * @author Sandeep Phukan
 *
 */
public class stacksort {
	public void sort(Stack<Integer> s) {
		int x = 0;
		if (!s.isEmpty()) {
			x = s.pop();
			System.out.println("x= "+x);
			sort(s);
			for (int val : s) {
				System.out.print(" sort "+val + " ");
			}
			insert(s, x);
			System.out.println("");
			for (int val : s) {
				System.out.print(" insert "+val + " ");
			}
			System.out.println("");
			
		}
	}

	// At each step check if stack.peek < x, and insert below top recursively
	public void insert(Stack<Integer> s, int x) {
		if (!s.isEmpty() && s.peek() >= x) {
			int y = s.pop();
			insert(s, x);
			s.push(y);
		} else {
			s.push(x);
		}
	}

	public static void main(String[] a) {
		stacksort ss = new stacksort();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(4);
		stack.push(1);
		stack.push(2);
		for (int val : stack) {
			System.out.print("initial "+val + " ");
		}
		ss.sort(stack);
		for (int val : stack) {
			System.out.print(val + " ");
		}
	}
}