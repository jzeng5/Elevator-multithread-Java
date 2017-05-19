

public class  BuildingManager {
	//array that stores 5 building floors
	private static BuildingFloor[] floors;
	
	//initialization 
	public BuildingManager(){
		floors = new BuildingFloor[5];
		for (int i = 0; i < 5;i++){
			floors[i] = new BuildingFloor();
		}
	}
	

	//set passengers have arrived on certain floor from another floor
	public synchronized void elevatorArrival(int elevatorID,int curfloor, int passengers){
		floors[curfloor].setArrivedPassengers(elevatorID, passengers);		
	}
	//get passengers have arrived on certain floor from another floor
	public synchronized int getelevatorArrival(int elevatorID, int floor){
		return floors[floor].getArrivedPassengers(elevatorID);
	}
	
	//set the number of passengers request to destination floor from current floor
	public  synchronized void setFloorRequest(int currentfloor,int destfloor,int passengers ){
		floors[currentfloor].addDestinationRequest(destfloor,passengers);
	}
	//get the number of passengers request to destination floor from current floor
	public  synchronized int getFloorRequest(int currentfloor,int destfloor,int passengers ){
			return floors[currentfloor].getDestinationRequest(destfloor);
	}
	//set the approaching elevator ID
	public synchronized void setapproachingElevator(int floor, int elevatorID)
	{
		floors[floor].setApproachingElevator(elevatorID);
	}
	
	//return number of passenger wants to travel from this floor to destination floor
	public synchronized int getCurrentRequest(int currentfloor,int destfloor){
		return floors[currentfloor].getPassengerRequests(destfloor);
	}
	//set the number of request to a specific floor 
	public synchronized void setCurrentRequest(int requestfloor,int destination,int passengers){
			floors[requestfloor].setPassengerRequests(destination,passengers);
	}
		
	//set the approaching elevator to a certain floor
	public synchronized int getApproachingElevator(int floor){
		return floors[floor].getApproachingElevator();
	}
	//locate the request when the elevator is empty
	public synchronized int locateRequest(int elevatorID){
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				if(getCurrentRequest(i,j) > 0 && getApproachingElevator(i) == -1){
					setapproachingElevator(i, elevatorID);
					return i;
				}
			}
		}
		return -1;
		
	}

	//print the states of each floor
	public void printStates()
	{
		for(int i = 0; i < 5; ++i)
		{
			if(floors[i] != null)
			{
				System.out.println("Floor " + i + ":");
				floors[i].printFloorStates();
				System.out.print("The total number of passengers who exited from elevators 0, 1, 2, 3, and 4 are, respectively: ");
				for (int j = 0; j < 5; j++){
					System.out.print(floors[i].getArrivedPassengers(j)+" ");
					
				}
				System.out.println();
			}
		}
	}

	
	 

	
	

}
