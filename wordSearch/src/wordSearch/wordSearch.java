package wordSearch;

public class wordSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "SEED";
		boolean result = false;
		for(int i=0;i<board.length; i++)
		{
			for(int j=0;j<board[0].length;j++)
			{
				 if(dfs(board,word,i,j,0))
					 result = true;
			}
		}
		System.out.println(result);
		for(int i=0;i<board.length; i++)
		{
			for(int j=0;j<board[0].length;j++)
			{
				System.out.println(board[i][j]);
			}
		}

	}
	public static boolean dfs(char[][] board, String word, int i, int j, int k)
	{
		int m = board.length;
		int n = board[0].length;
		char temp;
		if(i>=m || j>=n || i<0 || j<0){
			//temp = board[i][j];
			return false;}
		temp = board[i][j];
		if(board[i][j] == word.charAt(k))
		{
		//	temp = board[i][j];
			board[i][j] = '#';
			if(k == word.length()-1){
				board[i][j] = temp;
				return true;
			}
			else if(dfs(board,word, i+1,j,k+1)||dfs(board,word, i-1,j,k+1)||dfs(board,word, i,j+1,k+1)||dfs(board,word, i,j-1,k+1))
			{
				board[i][j] = temp;
				return true;
			}
			
		}
		board[i][j] = temp;
		
		return false;
	}

}
