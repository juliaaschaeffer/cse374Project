
public class ourAlgorithm 
{
	String alone = "yes"; 
	int numOfStops; 
	int temperature = 80, percepitation = 0; 
	// initialized as values that will not trigger weather code
	int countStops = 0; 
	int countOutdoor = 0; 
	int currentStop = 0; 
	int[][] matrix = {}; 
	int[] stopsHit = new int[numOfStops - 1]; 
	int distance = 0; 
	
	public static void main(String[] args) 
	{

	}
	
	String getPath()
	{
		while(countStops < stopsHit.length)
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
			if(used == false && i % 2 == 0 && matrix[currentStop][i] < smallest)
			{
				smallest = matrix[currentStop][i]; 
				output = i; 
			}
		}
		
		return output; 
	}
	
	int findClosest(int currentStop)
	{
		int output = 0; 
		
		return output; 
	}
	
	
	
	
}