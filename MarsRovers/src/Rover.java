public class Rover{
	public Rover() {
    }
	int xpos = 0;
	int ypos = 0;
    int direction = 0;
    // Using directions and move_offset for moving up, down, left and right in grid
    String directions = "NESW";
    int[][] move_offset = {{0,1},{1,0},{0,-1},{-1,0}};
    int dir_cnt=directions.length();
    public int upperRight_xpos;
	public int upperRight_ypos;
	
	//Upper right corner of grid set
	public void setPosition(int x, int y){
		upperRight_xpos=x;
		upperRight_ypos=y;
	}
	
    // Keeps track of position of rover throughout the movement
    public void setPosition(int x, int y, int direction) {
        this.xpos = x;
        this.ypos = y;
        this.direction = direction;
    }
    
    // Gets direction back from index 
    public char convert(int index)
    {
    	return directions.charAt(index);
    }
    
    //Gives index of direction to move from NSEW 
    public int convertToIndex(char c)
    {
    	return directions.indexOf(c);
    }
    
    // To get x-coordinate
    public int getPositionX() {
    	return xpos;
    }
    
    // To get y-coordinate
    public int getPositionY() {
    	return ypos;
    }
    
    // To get direction
    public int getPositionDirection() {
    	return direction;
    }
    
	public String rover_movement(String initPosition, String directions)
	{
		String output="";
		//Parse input and convert to required format
		String[] initPosition_splitarr = initPosition.split(" ");
		int xval= Integer.parseInt(initPosition_splitarr[0]);
		int yval = Integer.parseInt(initPosition_splitarr[1]);
		int dir_pos = convertToIndex(initPosition_splitarr[2].charAt(0));
		
		//Check if inside the grid. If inside set the initial positions
		if(xval<=upperRight_xpos && yval<=upperRight_ypos && xval>=0 && yval>=0)
			setPosition(xval,yval,dir_pos);
		else
			return "Error: Position of rover is outside the grid";
		
		for (char dir : directions.toCharArray()) {
			move(dir);
        }
		//Append latest coordinates to give output
		output = getPositionX() +" "+getPositionY()+" "+convert(getPositionDirection());
		return output;
	}
	
	public void move(char instruction) {
		// To do the required operation
		if(instruction =='L')
    		turnLeft();
        else if(instruction =='R')
            turnRight();
        else if(instruction =='M')
        	MoveForward();
    }
	
	public void turnRight()
	{
		int new_dir_pos = (getPositionDirection()+1)%dir_cnt;
		//Check if inside the grid. If inside, set new direction by moving one character forward in NESW
		if(new_dir_pos>=0 && new_dir_pos<dir_cnt)
			setPosition(getPositionX(),getPositionY(),new_dir_pos);
	}
	
	public void turnLeft()
	{
		int new_dir_pos =(getPositionDirection()-1+dir_cnt)%dir_cnt;
		//Check if inside the grid . If inside, set new direction by moving one character backward in NESW
		if(new_dir_pos>=0 && new_dir_pos<dir_cnt)
			setPosition(getPositionX(),getPositionY(),new_dir_pos);
	}
	
	public void MoveForward()
	{
		int pos = getPositionDirection();
		int new_xval = getPositionX()+move_offset[pos][0];
		int new_yval = getPositionY()+move_offset[pos][1];
		//Check if inside the grid. If inside, set new position by adding appropriate move offset to previous coordinates
		if(new_xval<=upperRight_xpos && new_yval<=upperRight_ypos && new_xval>=0 && new_yval>=0)
			setPosition(new_xval,new_yval,pos);
	}
}

