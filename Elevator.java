/*
 * Phan, Tony (43284383)
 * Zeng, Jessica (57314018)
*/

import java.util.ArrayList;

public class Elevator implements Runnable {
	//the current elevator id
	private int elevatorID;
	//the current floor elevator is on
	private int currentFloor;
	//number of passengers inside the elevator
	private int numPassengers;
	//total loaded passengers of this elevator
	private int totalLoadedPassengers;
	//total unloaded passengers of this elevator
	private int totalUnloadedPassengers;
	//the elevator events for this elevator
	private ArrayList<ElevatorEvent> moveQueue;
	//the passengers that go to such destinations
	private int[] passengerDestinations;
	//building manager object
	private BuildingManager BM;
	//indicate if the elevator should pick up passengers going down
	private boolean down;
	
	//constructor
	public Elevator(int elevatorID,BuildingManager manager){
		//set current elevator id
		this.elevatorID = elevatorID;
		BM = manager;
		//set default values for each attribute
		currentFloor = 0;
		numPassengers = 0;
		totalLoadedPassengers = 0;
		totalUnloadedPassengers = 0;
		moveQueue = new ArrayList<ElevatorEvent>();
		passengerDestinations = new int[5];
		down = true;
	}
	
	
	
	public void run(){
		//run when the thread is not empty
		while(!Thread.interrupted()){
			//check if there is any request when the elevator is available
			if (moveQueue.isEmpty() && numPassengers == 0) nextFloor();
			//execute elevator event when there is request
			if (!moveQueue.isEmpty()) {
				
				//get the elevator event
				ElevatorEvent cur = moveQueue.get(0);
				//when it is the expected time
				if (cur.getArrival() == SimClock.getTime()){
					//going down when there is no passengers going up
					down = true;
					//elevator moves to destination floor
					currentFloor = cur.getDestination();
					//get the number of passengers from the elevator who are going to this floor
					int passengers = passengerDestinations[currentFloor];
					
					//if there any one entering the floor, print and unload
					if (numPassengers > 0){
							System.out.println("Time:  "+ SimClock.getTime()+ "  Elevator "+ elevatorID + " is reaching floor "+currentFloor+" to unload "+ passengers +" passengers ");
							unloadPassengers(currentFloor,passengers);
							//update the floor states with elevator id and passengers
							BM.elevatorArrival(elevatorID, currentFloor, passengers);
							//remove the event
						    moveQueue.remove(0);	
					
					}
					//if the 
					else {

						pickUpPassengersUp(10);
						if (down == true){
							pickUpPassengersDown(10);
						}
						System.out.println("Time:  "+ SimClock.getTime()+ "  Elevator "+ elevatorID + " is reaching floor "+currentFloor+" to pickup "+ numPassengers+ " passengers "); 
						moveQueue.remove(0);
						BM.setapproachingElevator(currentFloor, -1);	
						
					}
				}
			}
		}
	}

		

		

	//pick up passengers who are going up 
	public void pickUpPassengersUp(int period){
		//for loop the floors that are greater than this floor
		for (int j = currentFloor+1; j < 5;j++){
			//the number of passengers that request to j floor
			 int requestpassengers = BM.getCurrentRequest(currentFloor, j);
			 //if there is any request
			 if (requestpassengers > 0){
				 //set the elevator traveled time
				 period =  period + 5 * Math.abs(j-currentFloor);
				 //create elevator event according to this request
				 createElevatorEvent(j,SimClock.getTime()+period);
				 //do not need to pick up passengers that are going down
				 down = false;
				 //load passengers
				 loadPassengers(j,requestpassengers);
				 //set the current floor request to 0
				 BM.setCurrentRequest(currentFloor, j, 0); 
				
				 
			 }
			 
		}
	}
	
	//execute when no one is going to above floor, check if anyone wants to travel down
	public void pickUpPassengersDown(int period){
		//for loop the floors below, same logic as pickup functions
		for (int j = currentFloor-1; j >= 0; j--){
			 int requestpassengers = BM.getCurrentRequest(currentFloor, j);
			 if (requestpassengers > 0){
				 period =  period + 5 * Math.abs(j-currentFloor);
				 createElevatorEvent(j,SimClock.getTime()+period);
				 loadPassengers(j,requestpassengers);
				 BM.setCurrentRequest(currentFloor, j, 0);
				
			 }
		}
	}
		
	//check the whole building if there any request
	public void nextFloor(){
		//use function in BM to locate request
		int nextfloor = BM.locateRequest(elevatorID);
		//if no elevator is approaching
		if(nextfloor != -1){
			//same logic up pick function
			int period = 5*Math.abs(nextfloor-currentFloor);
			System.out.println("Time:  "+ SimClock.getTime()+ "  Elevator "+ elevatorID + " is heading from floor " + currentFloor + " to floor "+nextfloor+" to pickup passengers ");
			createElevatorEvent(nextfloor,SimClock.getTime()+period);
			
		}	
	}
	
	//print the states for each elevator
	public void printElevatorState()
	{
		System.out.println("Elevator ID: " + elevatorID);
		System.out.println("The total number of passengers who entered this elevator is " + totalLoadedPassengers);
		for (int i = 0; i < 5; i++){
			System.out.println("The total number of passengers that exited this elevator on Floor " + i + " is " + BM.getelevatorArrival(elevatorID, i));
		}
		System.out.println("Total passengers unloaded: " + totalUnloadedPassengers);
		System.out.println("Current passengers: " + numPassengers);
	}

	
	//create event for elevator
	public void createElevatorEvent(int d, int t){
		ElevatorEvent EE = new ElevatorEvent(d,t);
		addMoveQueue(EE);
	}
	
	//add the event to the move queue for each elevator
	public void addMoveQueue(ElevatorEvent ee){
		moveQueue.add(ee);
	}
	
	//load passengers to elevator
	public void loadPassengers(int floor,int num){
		//increment number of passengers inside
		numPassengers+=num;
		//increment total loaded passengers
		totalLoadedPassengers+=num;
		//increment the number of passengers to this destination
		passengerDestinations[floor]+=num;
		
	}
	
	public void unloadPassengers(int floor,int num){
		//decrease number of passengers inside
		numPassengers-=num;
		//decrease total loaded passengers
		totalUnloadedPassengers+=num;
		//decrease the number of passengers to this destination
		passengerDestinations[floor]-=num;
	}
	

	
	
	

}
