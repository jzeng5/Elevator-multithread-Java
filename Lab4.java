/*
 * Phan, Tony (43284383)
 * Zeng, Jessica (57314018)
*/

import java.io.FileNotFoundException;

public class Lab4 {
	//main function that starts the whole program
	public static void main(String[] args) throws InterruptedException, FileNotFoundException{
		//create Elevator Simulation object
		ElevatorSimulation es = new ElevatorSimulation();
		//start simulation
		es.start();
		//print the states of elevators and floors when finished simulation
		es.printBuildingState();
	}

}
