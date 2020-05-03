import java.util.Scanner;

public class ourAlgorithm 
{
	static boolean efficientAlone = true; // variable to determine if efficiency alone should be used for path calculation 
	static int numOfStops = 19; 
	static int temperature, percipitation; 
	// initialized as values that will not trigger weather code
	static int countStops = 1; 
	static int countOutdoor, currentStop, distance; 
	static int[] stopsHit = new int[numOfStops]; 
	
	// indoor are even and outdoor odd
	static int[][] matrix = {
			{0, 4, 6, 3, 3, 8, 2, 3, 8, 6, 11, 6, 8, 4, 8, 4, 5, 18, 8}, // Armstrong 0
			{4, 0, 3, 5, 6, 5, 4, 4, 7, 7, 12, 8, 11, 2, 5, 1, 3, 14, 7}, // The Seal 1
			{6, 3, 0, 7, 7, 8, 6, 5, 4, 4, 14, 9, 13, 5, 8, 3, 6, 12, 10}, // Benton 2
			{3, 5, 7, 0, 7, 7, 5, 7, 12, 10, 9, 11, 12, 4, 7, 4, 6, 20, 5}, // Turtles/Sundial 3
			{3, 6, 7, 7, 0, 11, 2, 2, 6, 3, 14, 4, 6, 7, 11, 4, 8, 18, 11}, // Bachelor 4 
			{8, 5, 8, 7, 11, 0, 9, 8, 12, 10, 8, 12, 16, 4, 1, 5, 3, 19, 6}, // King Library 5
			{2, 4, 6, 5, 2, 9, 0, 3, 7, 5, 12, 5, 6, 6, 9, 3, 7, 20, 9}, // Shriver 6
			{3, 4, 5, 7, 2, 8, 3, 0, 5, 3, 15, 4, 7, 6, 10, 2, 7, 19, 10}, // Shideler 7
			{8, 7, 4, 12, 6, 12, 7, 5, 0, 3, 19, 10, 11, 9, 13, 7, 10, 13, 15}, // FBS 8
			{6, 7, 4, 10, 3, 10, 5, 3, 3, 0, 17, 5, 9, 8, 11, 5, 9, 16, 13}, // Cook Field 9
			{11, 12, 14, 9, 14, 8, 12, 15, 19, 17, 0, 18, 15, 11, 12, 11, 11, 27, 11}, // Rec Center 10
			{6, 8, 9, 11, 4, 12, 5, 4, 10, 5, 18, 0, 9, 10, 14, 7, 12, 22, 15}, // Pulley Tower 11
			{8, 11, 13, 12, 6, 16, 6, 7, 11, 9, 15, 9, 0, 13, 17, 10, 15, 24, 16}, // Western Dining 12
			{4, 2, 5, 4, 7, 4, 6, 6, 9, 8, 11, 10, 13, 0, 4, 1, 1, 15, 6}, // Slant Walk 13
			{8, 5, 8, 7, 11, 1, 9, 10, 13, 11, 12, 14, 17, 4, 0, 5, 3, 18, 18}, // Hall Auditorium 14
			{4, 1, 3, 4, 4, 5, 3, 2, 7, 5, 11, 7, 10, 1, 5, 0, 2, 15, 7}, // Friendship Tree 15
			{5, 3, 6, 6, 8, 3, 7, 7, 10, 9, 11, 12, 15, 1, 3, 2, 0, 17, 6}, // Alumni Hall 16
			{18, 14, 12, 20, 18, 19, 20, 19, 13, 16, 27, 22, 24, 15, 18, 15, 17, 0, 25}, // Yager Stadium 17
			{8, 7, 10, 5, 11, 6, 9, 10, 15, 13, 7, 15, 16, 6, 18, 7, 6, 25, 0} // Campus Avenue Building 18
					 };	
	
	static String[] names = {"Armstrong Student Center", "The Seal", "Benton Hall", "Turtles/Sundial", "Bachelor Hall", "King Library", 
			"Shriver Center", "Shideler Hall", "Farmer School of Business", "Cook Field", "Recreation Center", "Pulley Tower", 
			"Western Dining Hall", "Slant Walk", "Hall Auditorium", "Friendship Tree", "Alumni Hall", "Yager Stadium", "Campus Avenue Building"};
	
	public static void main(String[] args) 
	{
		boolean continueAlgo = true;
		
		while(continueAlgo == true) {
			Scanner reader = new Scanner(System.in);
					
			System.out.println("Please enter the approximate temperature on the day/time of the tour (in Fahrenheit): ");
			temperature = reader.nextInt();
			System.out.println("Please enter the approximate percentage of percipitation: ");
			percipitation = reader.nextInt();
			
			if (temperature <= 40 || temperature >= 85 || percipitation >= 60) {
				System.out.println("It appears that the weather is or will not be ideal.");
				System.out.println("Enter 1 to receive the ideal path and 2 to receive the most effecient path.");
				int choice = reader.nextInt();
				if(choice == 1)
					efficientAlone = false;
			}
			
			if(efficientAlone == true) {
				System.out.println("Please wait while the most efficient path is calculated...");
				getEfficientPath();
			}
			else {
				System.out.println("Please wait while the most ideal path is calculated...");
				getIdealPath();
			}
			
			System.out.println("\nTotal distance (in minutes): " + distance + "\n");
			
			System.out.println("Would you like to restart? Enter 1 for yes or 2 for no");
			int cont = reader.nextInt();
			
			if(cont == 1) {
				efficientAlone = true;
				temperature = 0; percipitation = 0;
				countStops = 1; countOutdoor = 0; currentStop = 0; distance = 0;
				int[] temp = new int[numOfStops];
				stopsHit = temp;
			}
			else {
				continueAlgo = false;
			}
		}
		
		System.out.println("Enjoy your tour!");
		
	}
	
	public static void getEfficientPath() {
		while(countStops < numOfStops) {
			int nextStop = findClosest(currentStop);
			stopsHit[countStops] = nextStop;
			
			distance += matrix[currentStop][nextStop];
			
			System.out.println("Stop #" + countStops + ": " + names[nextStop] + ". Distance: " + matrix[currentStop][nextStop] + " minutes.");
			currentStop = nextStop;
			countStops++;
		}
		System.out.println("Stop #" + countStops + ": " + "Armstrong Student Center. Distance: " + matrix[currentStop][0] + " minutes.");
		distance += matrix[currentStop][0];
	}
	
	public static void getIdealPath()
	{
		while(countStops < numOfStops)
		{
			int nextStop = 0; 
			if(countOutdoor == 3)
			{
				nextStop = findClosestIndoor(currentStop);
				stopsHit[countStops] = nextStop; 
				countOutdoor = 0; 
			}
			else
			{
				nextStop = findClosest(currentStop); 
				stopsHit[countStops] = nextStop; 
				if(nextStop % 2 == 0)
					countOutdoor = 0; 
				else
					countOutdoor++; 
			}
			distance += matrix[currentStop][nextStop]; 
			
			System.out.println("Stop #" + countStops + ": " + names[nextStop] + ". Distance: " + matrix[currentStop][nextStop] + " minutes."); 
			currentStop = nextStop; 
			countStops++; 
		}
		System.out.println("Stop #" + (countStops) + ": " + "Armstrong Student Center. Distance: " + matrix[currentStop][0] + " minutes."); 
		distance += matrix[currentStop][0];
	}
	
	
	public static int findClosest(int currentStop)
	{
		int smallest = Integer.MAX_VALUE; 
		int output = 0; 
		
		for(int i = 1; i < matrix[currentStop].length; i++)
		{
			boolean used = false; 
			
			// has to search all since its unordered
			for(int j = 0; j < stopsHit.length; j++)
			{
				if(stopsHit[j] == i)
					used = true; 
			}
			if(used == false && matrix[currentStop][i] < smallest && currentStop != i)
			{
				smallest = matrix[currentStop][i]; 
				output = i; 
			}
		}
		
		return output; 
	}
	
	public static int findClosestIndoor(int currentStop)
	{
		int smallest = Integer.MAX_VALUE; 
		int output = 0; 
		
		for(int i = 1; i < matrix[currentStop].length; i++)
		{
			boolean used = false; 
			
			// has to search all since its unordered
			for(int j = 0; j < stopsHit.length; j++)
			{
				if(stopsHit[j] == i)
					used = true; 
			}
			if(used == false && i % 2 == 0 && matrix[currentStop][i] < smallest  && currentStop != i)
			{
				smallest = matrix[currentStop][i]; 
				output = i; 
			}
		}
		
		return output; 
	}
	
}