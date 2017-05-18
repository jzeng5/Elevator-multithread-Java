/*
 * Phan, Tony (43284383)
 * Zeng, Jessica (57314018)
*/


public class BuildingFloor {
	
	private int[] totalDestinationRequests;
	private int[] arrivedPassengers;
	private int[] passengerRequests;
	private int approachingElevator;
	
	
	BuildingFloor(){
		//passengers that have requested to certain floor
		totalDestinationRequests = new int[5];
		//passengers that arrived on this floor from elevator 0-4
		arrivedPassengers = new int[5];
		//passengers that currently requested to certain floor
		passengerRequests = new int[5];
		//the elevator that is approaching this floor
		approachingElevator = -1;
	}
	
	//add the total number of passengers who have traveled to this floor
	public void addDestinationRequest(int destfloor,int num){
		totalDestinationRequests[destfloor] += num;
	}
	
	//get the total number of passengers who have traveled to this floor
	public int getDestinationRequest(int floor){
		return totalDestinationRequests[floor];
	}
	
	//set the total number of passengers who have arrived to this floor from such elevator
	public void setArrivedPassengers(int elevatorID,int num){
		arrivedPassengers[elevatorID] += num;
	}
	
	//get the total number of passengers who have arrived to this floor from such elevator
	public int getArrivedPassengers(int elevatorID){
		return arrivedPassengers[elevatorID];
	}
	
	//set the current number of passengers who have traveled to this floor
	public void setPassengerRequests(int floor, int num){
		passengerRequests[floor] = num;
	}
	
	//get the current number of passengers who have traveled to this floor
	public int getPassengerRequests(int floor){
		return passengerRequests[floor];
	}
	
	//set the elevator id that is approaching this floor
	public void setApproachingElevator(int ele){
		approachingElevator = ele;
	}
	
	//get the elevator id that is approaching this floor
	public int getApproachingElevator(){
		return approachingElevator;	
	}
	
	//print the states of each floor
	public void printFloorStates()
	{
		System.out.println("The elevator currently approaching this floor is " + approachingElevator);
		//total requested passengers
		int totalrequest = 0;
		//current requesting passengers
		int currentrequest = 0;
		//passengers arrived on this floor
		int arrivedpassengers = 0;
		for(int i = 0; i < 5; i++)
		{
			totalrequest += totalDestinationRequests[i];
			currentrequest += passengerRequests[i];
			arrivedpassengers += arrivedPassengers[i];
			
		}
		System.out.println("The total number of passengers who have requested an elevator on this floor is "+ totalrequest);
		System.out.println("The total number of passengers who have arrived on this floor is " + arrivedpassengers);
		System.out.println("The current number of passengers waiting on this floor is "+ currentrequest);
		
	}
	

}
