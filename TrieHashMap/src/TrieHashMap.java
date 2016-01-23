import java.util.*;
public class TrieHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie t = new Trie();
		t.insert("rosh");
		System.out.println(t.search("rosh"));

	}

	public static class TrieNode
	{
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		char c;
		boolean isLeaf;
		public TrieNode(){}
		public TrieNode(char c)
		{
			this.c = c;
		}
	}
	
	public static class Trie
	{
		private TrieNode root;
		 
	    public Trie() {
	        root = new TrieNode();
	    }
	 
	    public void insert(String word) {
	    	HashMap<Character, TrieNode> children = root.children;
	    	 
	        for(int i=0; i<word.length(); i++){
	            char c = word.charAt(i);
	 
	            TrieNode t;
	            if(children.containsKey(c)){
	                    t = children.get(c);
	            }else{
	                t = new TrieNode(c);
	                children.put(c, t);
	            }
	 
	            children = t.children;
	 
	            //set leaf node
	            if(i==word.length()-1)
	                t.isLeaf = true;    
	        }
		 }
	    
		    public boolean search(String word) {
		    	TrieNode t = searchNode(word);
		    	if(t!=null && t.isLeaf)
		    		return true;
		    	else
		    		return false;
		    }
	    
	    public boolean startsWith(String prefix) {
	        if(searchNode(prefix) == null) 
	            return false;
	        else
	            return true;
	    }
	    
	    public TrieNode searchNode(String str){
	    	TrieNode t = null;
	    	HashMap<Character, TrieNode> children = root.children;
	    	char[] word_arr = str.toCharArray();
	    	
	    	for(char c: word_arr)
	    	{
	    		if(children.containsKey(c))
	    		{
	    			t = children.get(c);
	    			children = t.children;
	    		}
	    		else
	    		{
	    		    return null;
	    		}
	    		
	    	}
	    	return t;
	    }
	}
}
