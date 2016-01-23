import java.util.*;

public class WordSearchTrie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"oath","pea","eat","rain"};
		char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		
		Solution s = new Solution();
		
		List<String> result = s.findWords(board, words);
		for(int i=0;i<result.size();i++)	
			System.out.println(result.get(i));
	}

public static class Solution {
	Set<String> result = new HashSet<String>();
	
	public List<String> findWords(char[][] board, String words[])
	{
		Trie t = new Trie();
		for(String word : words)
		{
			t.insert(word);
		}
		
		int m = board.length;
		int n = board[0].length;
		
		boolean[][] visited = new boolean[m][n];
		
		for(int i=0; i<m; i++)
		{
			for(int j=0;j<n;j++)
			{
				dfs(board,visited, "", i,j,t);
			}
		}
		return new ArrayList<String>(result);
	}
	
	public void dfs(char[][] board,  boolean[][] visited, String str, int i, int j,  Trie trie)
	{
		int m = board.length;
		int n = board[0].length;
		
		if(i>=m || j>=n || i<0 || j<0)
			return;
		
		if(visited[i][j])
			return;
		
		str = str + board[i][j];
		
		if(!trie.startsWith(str)){
			return;
		}
		if(trie.search(str))
		{
			result.add(str);
		}
		
		visited[i][j] = true;
		
		dfs(board, visited, str, i-1, j, trie);
        dfs(board, visited, str, i+1, j, trie);
        dfs(board, visited, str, i, j-1, trie);
        dfs(board, visited, str, i, j+1, trie);
		
		visited[i][j] = false;
		
	}
}

public static class TrieNode
{
	public TrieNode[] children = new TrieNode[26];
	public String item = "";
}

public static class Trie
{
	TrieNode root = new TrieNode();
	public boolean search(String word)
	{
		TrieNode node = root;
		char[] word_arr = word.toCharArray();
		for(char c : word_arr)
		{
			if(node.children[c-'a'] == null)
			{
				return false;
			}
			node = node.children[c-'a'];
		}
		if(node.item.equals(word))
			return true;
		else
			return false;
	}
	
	public void insert(String word)
	{
		TrieNode node = root;
		char[] word_arr = word.toCharArray();
		for(char c : word_arr)
		{
			if(node.children[c-'a'] == null)
			{
				node.children[c-'a'] =new TrieNode();
			}
			node = node.children[c-'a'];
		}
		node.item = word;
	}
	
	public boolean startsWith(String prefix)
	{
		TrieNode node = root;
		char[] prefix_arr = prefix.toCharArray();
		for(char c : prefix_arr)
		{
			if(node.children[c-'a'] == null)
			{
				return false;
			}
			node = node.children[c-'a'];
		}
		return true;
	}
}
}