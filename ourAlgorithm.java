
public class ourAlgorithm 
{
	String alone = "yes"; 
	int numOfStops = 19; 
	int temperature = 80, percepitation = 0; 
	// initialized as values that will not trigger weather code
	int countStops = 0; 
	int countOutdoor = 0; 
	int currentStop = 0; 
	int[] stopsHit = new int[numOfStops - 1]; 
	int distance = 0; 
	
	// indoor are even and outdoor odd
	int[][] matrix = {
			{0, 4, 6, 3, 3, 8, 2, 3, 8, 6, 11, 6, 8, 4, 8, 4, 5, 18, 8}, // Armstrong 
			{4, 0, 3, 5, 6, 5, 4, 4, 7, 7, 12, 8, 11, 2, 5, 1, 3, 14, 7}, // The Seal 
			{6, 3, 0, 7, 7, 8, 6, 5, 4, 4, 14, 9, 13, 5, 8, 3, 6, 12, 10}, // Benton
			{3, 5, 7, 0, 7, 7, 5, 7, 12, 10, 9, 11, 12, 4, 7, 4, 6, 20, 5}, // Turtles/Sundial
			{3, 6, 7, 7, 0, 11, 2, 2, 6, 3, 14, 4, 6, 7, 11, 4, 8, 18, 11}, // Bachelor
			{8, 5, 8, 7, 11, 0, 9, 8, 12, 10, 8, 12, 16, 4, 1, 5, 3, 19, 6}, // King Library 
			{2, 4, 6, 5, 2, 9, 0, 3, 7, 5, 12, 5, 6, 6, 9, 3, 7, 20, 9}, // Shriver
			{3, 4, 5, 7, 2, 8, 3, 0, 5, 3, 15, 4, 7, 6, 10, 2, 7, 19, 10}, // Shideler 
			{8, 7, 4, 12, 6, 12, 7, 5, 0, 3, 19, 10, 11, 9, 13, 7, 10, 13, 15}, // FBS
			{6, 7, 4, 10, 3, 10, 5, 3, 3, 0, 17, 5, 9, 8, 11, 5, 9, 16, 13}, // Cook Field 
			{11, 12, 14, 9, 14, 8, 12, 15, 19, 17, 0, 18, 15, 11, 12, 11, 11, 27, 11}, // Rec Center
			{6, 8, 9, 11, 4, 12, 5, 4, 10, 5, 18, 0, 9, 10, 14, 7, 12, 22, 15}, // Pulley Tower
			{8, 11, 13, 12, 6, 16, 6, 7, 11, 9, 15, 9, 0, 13, 17, 10, 15, 24, 16}, // Western Dining 
			{4, 2, 5, 4, 7, 4, 6, 6, 9, 8, 11, 10, 13, 0, 4, 1, 1, 15, 6}, // Slant Walk 
			{8, 5, 8, 7, 11, 1, 9, 10, 13, 11, 12 ,14, 17, 4, 0, 5, 3, 18, 18}, // Hall Auditorium 
			{4, 1, 3, 4, 4, 5, 3, 2, 7, 5, 11, 7, 10, 1, 5, 0, 2, 15, 7}, // Friendship Tree
			{5, 3, 6, 6, 8, 3, 7, 7, 10, 9, 11, 12, 15, 1, 3, 2, 0, 17, 6}, // Alumni Hall 
			{18, 14, 12, 20, 18, 19, 20, 19, 13, 16, 27, 22, 24, 15, 18, 15, 17, 0, 25}, // Yager Staduim 
			{8, 7, 10, 5, 11, 6, 9, 10, 15, 13, 7, 15, 16, 6, 18, 7, 6, 25, 0} // Campus Avenue Building 
					 };	
	
	
	
	
	public static void main(String[] args) 
	{
		
	}
	
	String getPath()
	{
		while(countStops < numOfStops)
		{
			int nextStop = 0; 
			if(countOutdoor > 3)
			{
				nextStop = findClosestIndoor(currentStop);
				stopsHit[countStops] = nextStop; 
				countOutdoor = 0; 
			}
			else
			{
				nextStop = findClosest(currentStop); 
				stopsHit[countStops] = nextStop; 
				if(nextStop % 2 != 0)
					countOutdoor = 0; 
				else
					countOutdoor++; 
			}
			countStops++; 
			distance = matrix[currentStop][nextStop]; 
			currentStop = nextStop; 
			
			return "Stop #" + countStops + ": " + nextStop + ". Distance: " + distance; 
		}
		return "Stop #" + (countStops + 1) + ": " + "Armstrong Student Center. distance: " 
				+ matrix[currentStop][0]; 
	}
	
	
	int findClosestIndoor(int currentStop)
	{
		int smallest = Integer.MAX_VALUE; 
		int output = 0; 
		
		for(int i = 0; i < matrix[currentStop].length; i++)
		{
			boolean used = false; 
			
			// has to search all since its unordered
			for(int j = 0; j < stopsHit.length; j++)
			{
				if(stopsHit[j] == i)
					used = true; 
			}
			if(used == false && matrix[currentStop][i] < smallest)
			{
				smallest = matrix[currentStop][i]; 
				output = i; 
			}
		}
		
		return output; 
	}
	
	int findClosest(int currentStop)
	{
		int smallest = Integer.MAX_VALUE; 
		int output = 0; 
		
		for(int i = 0; i < matrix[currentStop].length; i++)
		{
			boolean used = false; 
			
			// has to search all since its unordered
			for(int j = 0; j < stopsHit.length; j++)
			{
				if(stopsHit[j] == i)
					used = true; 
			}
			if(used == false && i % 2 == 0 && matrix[currentStop][i] < smallest)
			{
				smallest = matrix[currentStop][i]; 
				output = i; 
			}
		}
		
		return output; 
	}
	
	
}