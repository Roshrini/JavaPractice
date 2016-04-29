import java.io.File;
import java.util.Scanner;

public class MarsRovers {

	public static void main(String[] args) {
        
		try {
			Scanner input = new Scanner(System.in);
            System.out.print("Enter the file name with extention : ");
            File file = new File(input.nextLine());
            Scanner inputFile = new Scanner(file);
            String grid="";
            Rover rover = new Rover();
            if (inputFile.hasNextLine()) 
            	grid = inputFile.nextLine();
            // Get grid upper rightmost coordinates
            if(grid!=""){
            	String[] init = grid.split(" ");
            	rover.setPosition(Integer.parseInt(init[0]),Integer.parseInt(init[1]));
            }
      
            while (inputFile.hasNextLine()) {
            	// Get initial position and series of instructions for movement 
                String initPosition = inputFile.nextLine();
                String directions = inputFile.nextLine();
                if(initPosition!=null || directions!=null)
                	System.out.println(rover.rover_movement(initPosition.toUpperCase(), directions.toUpperCase()));

            }
            inputFile.close();
            input.close();
        } catch (Exception ex) {
        	System.out.println("File not found");
        }
	}

}
